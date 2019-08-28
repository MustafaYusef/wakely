package com.mustafayusef.wakely.data

data class categoreResponse(
    val `data`: DataCategore,
    val message: String,
    val success: Boolean
)
data class DataCategore(
    val role: Int,
    val token: String
)