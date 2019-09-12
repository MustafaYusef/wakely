package com.mustafayusef.wakely.data.profile

data class User(
    val _id: String,
    val email: String,
    val isActive: Boolean,
    val name: String,
    val phone: String,
    val role: Int
)