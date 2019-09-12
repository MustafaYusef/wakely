package com.mustafayusef.wakely.data

data class BannersResponse(
    val `data`: List<DataBanners>,
    val message: String,
    val success: Boolean
)
data class DataBanners(
    val title: String,
    val isActive: Boolean,
    val _id:String,
    val image:String,
    val companyId:String

)