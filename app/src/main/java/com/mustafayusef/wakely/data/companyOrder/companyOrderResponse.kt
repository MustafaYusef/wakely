package com.mustafayusef.wakely.data.companyOrder

data class companyOrderResponse(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)