package com.trycatchprojects.fitzoneyourgymguide.models

data class AllExerciseByCategoryPojoItem(
    val cat_difficulty: String,
    val description: String,
    val exercise_equipments: String,
    val exercise_muscles: String,
    val exercise_type: String,
    val id: String,
    val image: String,
    val name: String,
    val steps: String,
    val timimg: String,
    val url: String
)