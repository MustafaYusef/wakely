package com.mustafayusef.wakely.ui.compSection

import com.mustafayusef.wakely.data.categoreResponse

interface SecLesener {
    fun OnStart()
    fun OnSuccess(response:categoreResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
}