package com.tahaalangar.paniwala.pojo

data class CartItem(
    var id: String = "",
    var productId: String = "",
    var productName: String = "",
    var price: Double = 0.0,
    var quantity: Int = 1,
    var liter:Double=0.0,
    var milliLiter:Int=0,
    val image: String = ""
)
