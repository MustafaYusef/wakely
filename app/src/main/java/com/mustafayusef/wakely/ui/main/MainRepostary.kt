package com.mustafayusef.wakely.ui.main


import com.mustafayusef.wakely.data.categoreResponse
import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.network.myApi
import com.mustafayusef.wakely.utils.SafeApiRequest

class MainRepostary(val api: myApi): SafeApiRequest(){

    suspend fun getCategore(token:String,compId:String):categoreResponse{

        return SafeRequest{
            api.GetCategore(token,compId)
        }}



}