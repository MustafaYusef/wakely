package com.mustafayusef.wakely.data

data class provRes(
    val `data`: List<ProvData>,
    val message: String,
    val success: Boolean
)
data class ProvData(
    val _id: String,
    val isActive: Boolean,
    val name: String
)