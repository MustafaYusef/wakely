package com.mustafayusef.wakely.ui.orders.companyOrders

import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.companyOrder.companyOrderResponse
import com.mustafayusef.wakely.data.loginResponse

interface OrdersCompanyLesener {
    fun OnStart()

    fun onFailer(message:String)
    fun onFailerNet(message:String)

    fun OnSuccess(message: companyOrderResponse)

    fun OnSuccessAccept(message: acceptRes)

}