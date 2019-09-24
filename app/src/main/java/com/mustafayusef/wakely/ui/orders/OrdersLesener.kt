package com.mustafayusef.wakely.ui.orders

import com.mustafayusef.wakely.data.order.ordersRes

interface OrdersLesener {
    fun OnStart()
    fun onFailer(message:String)
    fun onFailerNet(message:String)
    fun OnSuccessGetOrders(message: ordersRes)


}