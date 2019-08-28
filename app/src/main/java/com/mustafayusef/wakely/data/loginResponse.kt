package com.mustafayusef.wakely.data

data class loginResponse(
    val `data`: DataLogin,
    val message: String,
    val success: Boolean
)
data class DataLogin(
    val role: Int,
    val token: String
)