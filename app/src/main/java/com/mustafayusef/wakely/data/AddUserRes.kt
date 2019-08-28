package com.mustafayusef.wakely.data

data class AddUserRes(
    val `data`: AddUserData,
    val message: String,
    val success: Boolean
)
data class AddUserData(
    val id: String
)