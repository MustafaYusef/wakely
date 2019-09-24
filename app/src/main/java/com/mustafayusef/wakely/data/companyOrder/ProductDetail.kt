package com.mustafayusef.wakely.data.companyOrder

data class ProductDetail(
    val description: String,
    val productPrices: List<ProductPrice>,
    val title: String
)