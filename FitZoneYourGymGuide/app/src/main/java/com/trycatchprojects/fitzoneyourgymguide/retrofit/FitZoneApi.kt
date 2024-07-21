package com.trycatchprojects.fitzoneyourgymguide.retrofit

import com.trycatchprojects.fitzoneyourgymguide.models.AllExerciseByCategoryPojoItem
import com.trycatchprojects.fitzoneyourgymguide.models.CategoryListPojoItem
import com.trycatchprojects.fitzoneyourgymguide.models.FoodListPojoItem
import com.trycatchprojects.fitzoneyourgymguide.models.SingleExercisePojoItem
import com.trycatchprojects.fitzoneyourgymguide.models.SingleFoodPojoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FitZoneApi {

    @GET("v3/fit_zone/category_list")
    fun getCategoryList(): Call<List<CategoryListPojoItem>>

    @GET("v3/fit_zone/exercise_list")
    fun getAllExerciseByCategory(@Query("category_id") categoryId: String): Call<List<AllExerciseByCategoryPojoItem>>

    @GET("v3/fit_zone/single_exercise")
    fun getSingleExercise(@Query("id") exerciseId: String): Call<List<SingleExercisePojoItem>>

    @GET("v3/fit_zone/food_list")
    fun getFoodList(): Call<List<FoodListPojoItem>>

    @GET("v3/fit_zone/single_food")
    fun getSingleFood(@Query("id") foodId: String): Call<List<SingleFoodPojoItem>>

}