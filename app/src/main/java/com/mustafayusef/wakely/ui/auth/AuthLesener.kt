package com.mustafayusef.wakely.ui.auth

import com.mustafayusef.wakely.data.loginResponse

interface AuthLesener {
    fun OnStart()
    fun OnSuccess(response:loginResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
}