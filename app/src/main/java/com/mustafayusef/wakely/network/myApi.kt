package com.mustafayusef.wakely.network

import androidx.lifecycle.LiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.companyOrder.companyOrderResponse
import com.mustafayusef.wakely.data.nationalProduct.National
import com.mustafayusef.wakely.data.order.ordersRes
import com.mustafayusef.wakely.data.profile.profile
import com.mustafayusef.wakely.data.singal.singelCompany
import com.mustafayusef.wakely.ui.Products.AddItem
import com.mustafayusef.wakely.ui.auth.LogIn
import com.mustafayusef.wakely.ui.auth.LoginBody
import com.mustafayusef.wakely.ui.auth.addUserBody
import com.mustafayusef.wakely.ui.orders.companyOrders.accBody
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import java.util.*
import retrofit2.Response
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit


interface myApi {


    @POST("login")
 suspend fun Login(
     @Body log: LogIn
 ):Response<loginResponse>

    @GET("get_single_company/{id}")
    suspend fun getSingel(
        @Header("access_token") token:String,
        @Path("id") compId: String
    ):Response<singelCompany>

    @FormUrlEncoded
    @POST("add_rate")
    suspend fun ratting(
        @Header("access_token") token:String,
        @Field("rate") rate:Double,@Field("companyId") com:String
    ):Response<acceptRes>

    @GET("user_profile")
    suspend fun Profile(
        @Header("access_token") token:String
    ):Response<profile>

    @Headers("Content-Type: application/json")
    @GET("orders")
    suspend fun getCart(
        @Header("access_token") token:String
    ):Response<Cart>

    @Headers("Content-Type: application/json")
    @GET("get_categories/{compId}")
    suspend fun GetCategore(
       @Header("access_token") token:String, @Path("compId") compId:String
    ):Response<categoreResponse>

    @GET("get_banners")
    suspend fun getBanners(
    ):Response<BannersResponse>

    @GET("get_companies?")
    suspend fun getCompany(
        @Query("p")page:Int,@Query("s")s:Int
    ):Response<ShopsResponse>


    @GET("get_products/{id}?")
    suspend fun getProduct(
        @Header("access_token") token: String,
        @Path("id") id:String,
        @Query("p")page:Int, @Query("s")s:Int
    ):Response<productsResponse>

    @GET("get_shops?")
    suspend fun getShops(
        @Query("p")page:Int,@Query("s")s:Int
    ):Response<ShopsResponse>

    @GET("get_shops?")
    suspend fun getShopsAll(
        @Query("p")page:Int,@Query("s")s:Int
    ):Response<ShopsResponse>


    @GET("get_discounted_companies")
    suspend fun getDisscountComp():Response<disscountCompany>

    @GET(" get_discounted_products/{id}")
    suspend fun getDisscountProducts(
       @Path("id") id:String
    ):Response<productsResponse>


    @GET("get_cities/{id}")
    suspend fun GetCity(
   @Path("id") provId:String ):Response<provRes>


    @GET("get_provinces")
    suspend fun GetProv():Response<provRes>


    @Multipart
    @POST("add_shop")
    suspend fun AddShops(
        @Header("access_token") token:String,@Part("title")title:RequestBody,@Part("phone") phone:RequestBody
        ,@Part("provinceId") prov:RequestBody,@Part("cityId") prov1:RequestBody
        ,@Part("nearLocation") prov2:RequestBody,@Part image: MultipartBody.Part
        ,@Part ("userId")userId:RequestBody
    ):Response<AddUserRes>



    @POST("add_user")
    suspend fun AddUser(
        @Header("access_token") token:String,
        @Body body: addUserBody
    ):Response<AddUserRes>


    @POST("new_order")
    suspend fun AddToCart(
        @Header("access_token") token:String,
        @Body body: AddItem
    ):Response<productsResponse>

    @Headers("Content-Type: application/json")
    @GET("get_checkouts")
    suspend fun getOrdersHistory(
        @Header("access_token") token: String
    ):Response<ordersRes>


    @POST("checkout")
    suspend fun sendOrder(
        @Header("access_token") token:String
    ):Response<productsResponse>

    @Multipart
    @POST("update")
    suspend fun Update(
        @Header("access_token") token:String,@Part("name") name:RequestBody,
        @Part("title") title: RequestBody,@Part("phone") phone: RequestBody,
        @Part image: MultipartBody.Part
    ):Response<loginResponse>

    @GET("get_national_products")
    suspend fun getNational():Response<National>


    @GET("company/get_checkouts")
    suspend fun getCompanyOrders(
        @Header("access_token") token:String
    ):Response<companyOrderResponse>

    @Headers("Content-Type: application/json")
    @POST("finsh_order")
    suspend fun AcceptOrder(
        @Header("access_token") token:String,
        @Body bod: accBody
    ):Response<acceptRes>

    companion object{

        operator fun invoke(
            networkIntercepter:networkIntercepter
        ):myApi{
            val spec = ConnectionSpec.Builder ( ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                )
                .build()
            val spec1 = ConnectionSpec.Builder ( ConnectionSpec.CLEARTEXT)
                .build()


            val client = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(50,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(networkIntercepter)
                .connectionSpecs(Collections.singletonList(spec))
                .connectionSpecs(Collections.singletonList(spec1))
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("http://api.alwakiel.com/v1/")
               // .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(myApi::class.java)
        }
    }
}
