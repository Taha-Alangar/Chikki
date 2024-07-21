package com.tahaalangar.mobileshoppingapi

data class Products(
    val limit: Int,
    val products: List<ProductDetail>,
    val skip: Int,
    val total: Int
)