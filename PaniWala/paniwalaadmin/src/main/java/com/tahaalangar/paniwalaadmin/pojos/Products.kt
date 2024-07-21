package com.tahaalangar.paniwalaadmin.pojos

import java.io.Serializable

data class Products(
    val productId: String = "",
    val name: String = "",
    val category: String = "",
    val price: Double = 0.0,
    var stocks: Int = 0,
    val liter: Double = 0.0,
    val milliLiter: Int = 0,
    var imageUrl: String = ""
):Serializable

