package com.mustafayusef.wakely.data

data class BannersResponse(
    val `data`: DataCategore,
    val message: String,
    val success: Boolean
)
data class DataBanners(
    val role: Int,
    val token: String
)