package com.mustafayusef.wakely.data.order

data class ordersRes(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)