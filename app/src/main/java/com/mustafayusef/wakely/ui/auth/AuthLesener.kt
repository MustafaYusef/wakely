package com.mustafayusef.wakely.ui.auth

import com.mustafayusef.wakely.data.loginResponse
import com.mustafayusef.wakely.data.profile.profile

interface AuthLesener {
    fun OnStart()
    fun OnSuccess(response:loginResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)

    fun OnSuccessProfile(message: profile)
    fun OnSuccessUpdate(response:loginResponse)
}