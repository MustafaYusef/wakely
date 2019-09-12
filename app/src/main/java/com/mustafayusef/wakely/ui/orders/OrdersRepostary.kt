package com.mustafayusef.wakely.ui.orders


import com.mustafayusef.wakely.data.order.ordersRes
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class OrdersRepostary(val api: myApi): SafeApiRequest(){


    suspend fun GetOrdersHistory(token:String): ordersRes {

        return SafeRequest{
            api.getOrdersHistory(token)
        }}

}
data class AddItem(
    var productId:String,
    var quantity:Int,
    var companyId:String,
    var priceId:String
)