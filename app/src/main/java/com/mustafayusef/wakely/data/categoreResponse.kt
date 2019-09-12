package com.mustafayusef.wakely.data

import android.media.Image

data class categoreResponse(
    val `data`: List<DataCategore>,
    val message: String,
    val success: Boolean
)
data class DataCategore(
     val isActive:Boolean,
     val _id:String,
     val name:String,
     val image: String

)