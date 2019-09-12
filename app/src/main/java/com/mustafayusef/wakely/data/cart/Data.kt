package com.mustafayusef.wakely.data.cart

import java.io.Serializable

data class Data(
    val _id: String,
    val company: Company,
    val companyId: String,
    val isActive: Boolean,
    val product: Product,
    val productId: String,
    val quantity: Int,
    val status: Int,
    val totalPrice: Int,
    val userId: String
):Serializable