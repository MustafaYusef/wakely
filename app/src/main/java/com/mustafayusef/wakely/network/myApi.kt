package com.mustafayusef.wakely.network

import com.mustafayusef.holidaymaster.networks.networkIntercepter
import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.ui.auth.LoginBody
import com.mustafayusef.wakely.ui.auth.addUserBody
import com.mustafayusef.wakely.ui.auth.location
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Headers
import java.util.concurrent.TimeUnit


interface myApi {


    @POST("login")
 suspend fun Login(
     @Body log: LoginBody
 ):Response<loginResponse>

    @Headers("Content-Type: application/json")
    @GET("get_categories/{compId}")
    suspend fun GetCategore(
       @Header("access_token") token:String, @Path("compId") compId:String
    ):Response<categoreResponse>

    @GET("get_banners")
    suspend fun getBanners(
    ):Response<BannersResponse>

    @GET("get_companies")
    suspend fun getCompany(
    ):Response<companyResponse>

    @GET("get_shops")
    suspend fun getShops(
    ):Response<loginResponse>

    @GET("get_cities/{id}")
    suspend fun GetCity(
   @Path("id") provId:String ):Response<provRes>

    @GET("get_provinces")
    suspend fun GetProv():Response<provRes>

    @Multipart
    @POST("add_shop")
    suspend fun AddShops(
        @Header("access_token") token:String,@Part("title")title:RequestBody,@Part("phone") phone:RequestBody
        ,@Part("location")location: location,@Part image: MultipartBody.Part
        ,@Part ("userId")userId:RequestBody
    ):Response<AddUserRes>


    @Headers("Content-Type: application/json")
    @POST("add_user")
    suspend fun AddUser(
        @Header("access_token") token:String,
        @Body body: addUserBody
    ):Response<AddUserRes>

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
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(20,TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(networkIntercepter)
                .connectionSpecs(Collections.singletonList(spec))
               // .connectionSpecs(Collections.singletonList(spec1))
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://alwakel.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())

                .build().create(myApi::class.java)
        }
    }
}
