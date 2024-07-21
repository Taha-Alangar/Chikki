package com.tahaalangar.onlineshoppingapp.pojos

data class CartPojo(
    val discountedTotal: Double,
    val id: Int,
    val products: List<ProductXX>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)