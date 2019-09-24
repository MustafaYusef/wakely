package com.mustafayusef.wakely.ui.compSection

import com.mustafayusef.wakely.data.acceptRes
import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.productsResponse
import com.mustafayusef.wakely.data.singal.singelCompany

interface SecLesener {
    fun OnStart()
    fun OnSuccess(
        response: categoreResponse,
        singelRes: singelCompany
    )
    fun OnSuccessProduct(response:productsResponse)

    fun onFailer(message:String)
    fun onFailerNet(message:String)
   fun OnSuccessRate(message:acceptRes)
}