package com.mustafayusef.wakely.data

import com.mustafayusef.wakely.ui.auth.location

data class cartResponse(
    val `data`: List<DataCrt>,
    val message: String,
    val success: Boolean
)



data class CompanyCart(
    val _id: String,
    val image: String,
    val isActive: Boolean,
    val location: location,
    val phone: String,
    val title: String
)

data class DataCrt(
    val _id: String,
    val company: CompanyCart,
    val companyId: String,
    val isActive: Boolean,
    val product: productData,
    val productId: String,
    val quantity: Int,
    val status: Int,
    val totalPrice: Int,
    val userId: String
)
