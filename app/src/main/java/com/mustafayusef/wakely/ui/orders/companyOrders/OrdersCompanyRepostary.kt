package com.mustafayusef.wakely.ui.orders.companyOrders


import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.companyOrder.companyOrderResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class OrdersCompanyRepostary(val api: myApi): SafeApiRequest(){


    suspend fun GetOrdersCompany(token:String): companyOrderResponse {

        return SafeRequest{
            api.getCompanyOrders(token)
        }}

    suspend fun Accept(token:String, id:String,
                        status:Int,
                        notes:String): acceptRes {
      var body=accBody(id=id,status = status,notes = notes)
        return SafeRequest{
            api.AcceptOrder(token,body)
        }}
}
data class accBody(
    var id:String,
    var status:Int,
    var notes:String
)
