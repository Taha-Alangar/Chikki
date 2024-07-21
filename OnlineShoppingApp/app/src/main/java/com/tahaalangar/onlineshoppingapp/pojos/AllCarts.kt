package com.tahaalangar.onlineshoppingapp.pojos

data class AllCarts(
    val carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)