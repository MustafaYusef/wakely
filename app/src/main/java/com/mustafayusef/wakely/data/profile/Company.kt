package com.mustafayusef.wakely.data.profile

data class Company(
    val _id: String,
    val createdAt: String,
    val fullLocation: FullLocation,
    val image: String,
    val isActive: Boolean,
    val location: Location,
    val phone: String,
    val title: String
)