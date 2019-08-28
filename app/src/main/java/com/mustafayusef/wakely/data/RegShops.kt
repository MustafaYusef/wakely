package com.mustafayusef.wakely.data

data class RegShops(
    val image: String,
    val location: Location,
    val phone: String,
    val title: String,
    val userId: String
)
data class Location(
    val cityId: String,
    val nearLocation: String,
    val provinceId: String
)