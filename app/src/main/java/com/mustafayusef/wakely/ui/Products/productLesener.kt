package com.mustafayusef.wakely.ui.Products

import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart

interface productLesener {
    fun OnStart()
    fun OnSuccess(response:productsResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)

    fun OnSuccessAdd(message:productsResponse)
    fun OnSuccessGetCart(message: Cart)

    fun OnSuccessSend(message:productsResponse)



}