package com.mustafayusef.wakely.data.order

import java.io.Serializable

data class Order(
    val _id: String,
    val companyDetails: CompanyDetails,
    val createdAt: String,
    val isActive: Boolean,
    val location: Location,
    val notes: String,
    val price: Int,
    val productDetails: List<ProductDetail>,
    val products: List<Product>,
    val status: Int,
    val totalQuantities: Int,
    val userId: String
): Serializable