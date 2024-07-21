package com.trycatchprojects.maganlalchikki.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    var cartCount: Int
)
