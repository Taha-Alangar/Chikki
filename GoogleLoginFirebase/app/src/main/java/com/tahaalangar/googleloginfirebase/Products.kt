package com.tahaalangar.googleloginfirebase

data class Products(
    val id:String="",
    val type:String?=null,// "waterCan" or "coldDrink"
    val companyName:String?=null,
    val price: Double = 0.0,
    val stock: String ?=null,
    val liters: Double? = null, // for water can
)
