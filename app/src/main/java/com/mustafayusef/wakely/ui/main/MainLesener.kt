package com.mustafayusef.wakely.ui.main

import com.mustafayusef.wakely.data.categoreResponse

interface MainLesener {
    fun OnStart()
    fun OnSuccess(response:categoreResponse)
    fun onFailer(message:String)
    fun onFailerNet(message:String)
}