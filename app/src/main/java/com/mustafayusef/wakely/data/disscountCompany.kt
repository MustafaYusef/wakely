package com.mustafayusef.wakely.data

data class disscountCompany(
    val `data`: List<DataDissCount>,
    val message: String,
    val success: Boolean
)
data class Company(
    val _id: String,
    val createdAt: String,
    val image: String,
    val isActive: Boolean,
    val location: Location,
    val phone: String,
    val title: String
)

data class DataDissCount(
    val _id: String,
    val company: Company,
    val createdAt: String,
    val description: String,
    val discountPercentage: Int,
    val image: String,
    val isActive: Boolean
)