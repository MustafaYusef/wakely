package com.mustafayusef.wakely.data

data class ShopsResponse(
    val `data`: DataCategore,
    val message: String,
    val success: Boolean
)
data class DataShops(
    val role: Int,
    val token: String
)