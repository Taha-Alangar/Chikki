package com.tahaalangar.thee_mart

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products/categories")
    fun getCategoryList():Call<CategoryLists>

    @GET("products")
    fun getAllProducts():Call<Products>

    @GET("products/category/smartphones")
    fun getSmartPhoneProducts():Call<Products>

    @GET("products/category/laptops")
    fun getLaptopsProducts():Call<Products>

    @GET("products/category/fragrances")
    fun getFragrancesProducts():Call<Products>

    @GET("products/category/skincare")
    fun getSkincareProducts():Call<Products>

    @GET("products/category/groceries")
    fun getGroceriesProducts():Call<Products>

    @GET("products/category/home-decoration")
    fun getHomeDecorationProducts():Call<Products>

    @GET("products/category/furniture")
    fun getFurnitureProducts():Call<Products>

    @GET("products/category/tops")
    fun getTopsProducts():Call<Products>

    @GET("products/category/womens-dresses")
    fun getWomenDressProducts():Call<Products>

    @GET("products/category/womens-shoes")
    fun getWomenShoesProducts():Call<Products>

    @GET("products/category/mens-shirts")
    fun getMenShirtsProducts():Call<Products>

    @GET("products/category/mens-shoes")
    fun getMenShoesProducts():Call<Products>

    @GET("products/category/mens-watches")
    fun getMenWatchesProducts():Call<Products>

    @GET("products/category/womens-watches")
    fun getWomenWatchesProducts():Call<Products>

    @GET("products/category/womens-bags")
    fun getWomenBagsProducts():Call<Products>

    @GET("products/category/womens-jewellery")
    fun getWomenJewelleryProducts():Call<Products>

    @GET("products/category/sunglasses")
    fun getSunglassesProducts():Call<Products>

    @GET("products/category/automotive")
    fun getAutoMativeProducts():Call<Products>

    @GET("products/category/motorcycle")
    fun getMotorCycleProducts():Call<Products>

    @GET("products/category/lighting")
    fun getLightingProducts():Call<Products>

}