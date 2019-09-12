package com.mustafayusef.wakely.data

import java.io.Serializable
import java.io.SerializablePermission


data class productsResponse(
    val `data`:List<productData> ,
    val message: String,
    val success: Boolean
)
data class productData(
    val _id: String,
    val companyId: String,
    val createdAt: String,
    val description: String,
    val discountPercentage: Int,
    val isActive: Boolean,
    val productPrices: List<ProductPrice>,
    val title: String
):Serializable

data class ProductPrice(
    val _id: String,
    val image: String,
    val price: Int,
    val shortDescription: String
)