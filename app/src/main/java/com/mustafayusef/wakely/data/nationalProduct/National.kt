package com.mustafayusef.wakely.data.nationalProduct

import com.mustafayusef.wakely.data.productData

data class National(
    val `data`: List<productData>,
    val message: String,
    val success: Boolean
)