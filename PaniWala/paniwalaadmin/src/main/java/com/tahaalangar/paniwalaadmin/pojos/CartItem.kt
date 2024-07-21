package com.tahaalangar.paniwalaadmin.pojos

data class CartItem(
    var productName: String = "",
    var quantity: Int = 0,
    var price: Double = 0.0,
    var liter:Double=0.0,
    var milliLiter:Int=0,
    val image:String=""
)
