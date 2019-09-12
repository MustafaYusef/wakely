package com.mustafayusef.wakely.ui.Products.nationalProduct

import com.mustafayusef.wakely.data.*
import com.mustafayusef.wakely.data.cart.Cart
import com.mustafayusef.wakely.data.nationalProduct.National

interface NationalLesener {
    fun OnStart()
    fun OnSuccess(response:National)
    fun onFailer(message:String)
    fun onFailerNet(message:String)




}