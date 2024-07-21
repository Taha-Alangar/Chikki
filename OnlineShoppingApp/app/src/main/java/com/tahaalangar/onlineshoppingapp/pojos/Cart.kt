package com.tahaalangar.onlineshoppingapp.pojos

data class Cart(
    val discountedTotal: Double,
    val id: Int,
    val products: List<ProductX>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)