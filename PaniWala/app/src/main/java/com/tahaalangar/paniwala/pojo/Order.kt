package com.tahaalangar.paniwala.pojo

data class Order(
    var id: String = "",
    var userId: String = "",
    var address: String = "",
    var deliveryTime: String = "",
    var items: List<CartItem> = emptyList(),
    var status: String = "",
    val orderDate: String = ""

)
