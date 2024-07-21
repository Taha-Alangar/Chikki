package com.tahaalangar.paniwalaadmin.pojos

data class Order(
    var id: String = "",
    var userId: String = "",
    var userEmail: String = "", // New field to store user's name
    var address: String = "",
    var deliveryTime: String = "",
    var items: List<CartItem> = emptyList(),
    var status: String = "",
    val orderDate: String = ""

)
