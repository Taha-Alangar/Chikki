package com.tahaalangar.onlineshoppingapp.pojos

data class AllProduct(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)