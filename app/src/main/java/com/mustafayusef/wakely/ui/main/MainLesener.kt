package com.mustafayusef.wakely.ui.main

import com.mustafayusef.wakely.data.BannersResponse
import com.mustafayusef.wakely.data.ShopsResponse
import com.mustafayusef.wakely.data.disscountCompany


interface MainLesener {
    fun OnStart()
    fun OnSuccess(
        response: BannersResponse,
        shopsResponse: ShopsResponse,
        companyResponse: ShopsResponse,
        disscountResponse: disscountCompany
    )
    fun onFailer(message:String)
    fun onFailerNet(message:String)
    fun onSuccessAll(message:ShopsResponse)
    fun onSuccessAllComp(message:ShopsResponse)

}