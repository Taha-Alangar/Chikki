package com.tahaalangar.thee_mart

data class Products(
    val limit: Int,
    val products: List<ProductDetailsPojoClass>,
    val skip: Int,
    val total: Int
)