package com.tahaalangar.onlineshoppingapp.pojos

data class SearchPojo(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)