package com.mustafayusef.wakely.data

import com.mustafayusef.wakely.ui.auth.location

data class ordersResponse(
    val `data`: List<OrdersData>,
    val message: String,
    val success: Boolean
)



data class OrdersData(
    val __v: Int,
    val _id: String,
    val collectionId: String,
    val companyId: String,
    val createdAt: String,
    val isActive: Boolean,
    val location: location,
    val notes: String,
    val price: Int,
    val products: List<Product>,
    val status: Int,
    val totalQuantities: Int,
    val userId: String
)