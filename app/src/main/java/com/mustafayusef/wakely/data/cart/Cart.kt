package com.mustafayusef.wakely.data.cart

data class Cart(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)