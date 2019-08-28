package com.mustafayusef.wakely.ui.auth.Delegate

import com.mustafayusef.wakely.data.AddUserRes
import com.mustafayusef.wakely.data.provRes

interface DelegateLesener {
    fun OnStart()
    fun OnSuccess(response: AddUserRes)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
    fun OnSuccessProv(response: provRes)
    fun OnSuccessCity(response: provRes)
}