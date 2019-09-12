package com.mustafayusef.wakely.data.order

import java.io.Serializable

data class Order(
    val __v: Int,
    val _id: String,
    val companyId: String,
    val createdAt: String,
    val isActive: Boolean,
    val location: Location,
    val notes: String,
    val price: Int,
    val products: List<Product>,
    val status: Int,
    val totalQuantities: Int,
    val userId: String
):Serializable