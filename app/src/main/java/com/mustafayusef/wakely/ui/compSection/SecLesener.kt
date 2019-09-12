package com.mustafayusef.wakely.ui.compSection

import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.productsResponse

interface SecLesener {
    fun OnStart()
    fun OnSuccess(response:categoreResponse)
    fun OnSuccessProduct(response:productsResponse)

    fun onFailer(message:String)
    fun onFailerNet(message:String)
}