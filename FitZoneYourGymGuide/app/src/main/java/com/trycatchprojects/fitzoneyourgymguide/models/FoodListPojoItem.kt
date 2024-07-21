package com.trycatchprojects.fitzoneyourgymguide.models

import java.io.Serializable

data class FoodListPojoItem(
    val calories: String,
    val carbs: String,
    val description: String,
    val fats: String,
    val id: String,
    val image: String,
    val name: String,
    val protein: String,
    val sodium: String
): Serializable