package com.tahaalangar.paniwala.pojo

import java.io.Serializable

data class Products(
    var productId: String = "",
    val name: String = "",
    val category: String = "",
    val price: Double = 0.0,
    val stocks:Int=0,
    val liter: Double = 0.0,
    val miliLiter: Int = 0,
    var imageUrl: String = ""
): Serializable