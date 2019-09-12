package com.mustafayusef.wakely.data.cart

data class Company(
    val _id: String,
    val image: String,
    val isActive: Boolean,
    val location: Location,
    val phone: String,
    val title: String
)