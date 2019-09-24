package com.mustafayusef.wakely.data.companyOrder

import java.io.Serializable

data class Data(
    val _id: String,
    val cityDetails: CityDetails,
    val createdAt: String,
    val isActive: Boolean,
    val location: Location,
    val notes: String,
    val price: Int,
    val productDetails: List<ProductDetail>,
    val products: List<Product>,
    val provinceDetails: ProvinceDetails,
    val shopDetails: ShopDetails,
    val status: Int,
    val totalQuantities: Int,
    val userId: String
) : Serializable