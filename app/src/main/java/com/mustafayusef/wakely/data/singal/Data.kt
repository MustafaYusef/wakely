package com.mustafayusef.wakely.data.singal

import com.mustafayusef.wakely.data.LocationX

data class Data(
    val _id: String,
    val image: String,
    val isActive: Boolean,
    val location: LocationX,
    val phone: String,
    val rate: Int,
    val title: String
)