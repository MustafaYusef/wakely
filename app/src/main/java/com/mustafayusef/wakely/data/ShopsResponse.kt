package com.mustafayusef.wakely.data

data class ShopsResponse(
    val `data`: List<shopData>,
    val message: String,
    val success: Boolean
)
data class shopData(
    val _id: String,
    val image: String,
    val isActive: Boolean,
    val location: Location,
    val phone: String,
    val title: String
)
