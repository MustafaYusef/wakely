package com.mustafayusef.wakely.ui.Products


import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.nationalProduct.National
import com.mustafayusef.wakely.data.order.ordersRes
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class ProductRepostary(val api: myApi): SafeApiRequest(){


    suspend fun getProduct(token:String,SecId:String,pageNum:Int,Num:Int): productsResponse {

        return SafeRequest{
            api.getProduct(token,SecId,pageNum,Num)
        }}

    suspend fun getDisscountProducts(id:String): productsResponse {

        return SafeRequest{
            api.getDisscountProducts(id)
        }}

    suspend fun AddToCart(token:String,productId:String,quantity:Int,companyId:String,
                          priceId:String): productsResponse {
             var add=AddItem(productId=productId,quantity = quantity,companyId = companyId,
                 priceId = priceId)
        return SafeRequest{
            api.AddToCart(token,add)
        }}
    suspend fun getCart(token:String): Cart {

        return SafeRequest{
            api.getCart(token)
        }}

    suspend fun sendOrder(token:String): productsResponse {

        return SafeRequest{
            api.sendOrder(token)
        }}

    suspend fun GetOrdersHistory(token:String): ordersRes {

        return SafeRequest{
            api.getOrdersHistory(token)
        }}

    suspend fun National(): National {

        return SafeRequest{
            api.getNational()
        }}


}
data class AddItem(
    var productId:String,
    var quantity:Int,
    var companyId:String,
    var priceId:String
)