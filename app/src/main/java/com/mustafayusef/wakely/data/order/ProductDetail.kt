package com.mustafayusef.wakely.data.order

import java.io.Serializable

data class ProductDetail(
    val description: String,
    val productPrices: List<ProductPrice>,
    val title: String
): Serializable