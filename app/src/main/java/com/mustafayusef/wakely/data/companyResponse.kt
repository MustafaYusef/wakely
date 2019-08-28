package com.mustafayusef.wakely.data

data class companyResponse(
    val `data`: DataCategore,
    val message: String,
    val success: Boolean
)
data class Datacompany(
    val role: Int,
    val token: String
)