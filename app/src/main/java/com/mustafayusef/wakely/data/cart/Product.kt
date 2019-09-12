package com.mustafayusef.wakely.data.cart

import java.io.Serializable

data class Product(
    val _id: String,
    val companyId: String,
    val createdAt: String,
    val description: String,
    val discountPercentage: Int,
    val isActive: Boolean,
    val productPrices: List<ProductPrice>,
    val title: String
):Serializable