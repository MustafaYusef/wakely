package com.mustafayusef.wakely.data.order

import java.io.Serializable

data class Data(
    val createdAt: String,
    val order: List<Order>,
    val totalPrice: Int,
    val totalQuantities: Int
):Serializable