package com.tahaalangar.onlineshoppingapp.retrofit

import com.tahaalangar.onlineshoppingapp.pojos.AllCarts
import com.tahaalangar.onlineshoppingapp.pojos.AllProduct
import com.tahaalangar.onlineshoppingapp.pojos.CartPojo
import com.tahaalangar.onlineshoppingapp.pojos.CategoryList
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListPojo
import com.tahaalangar.onlineshoppingapp.pojos.SearchPojo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("products/category-list")
    fun getCategoryList(): Call<CategoryList>

    @GET("products")
    fun getAllProducts():Call<AllProduct>

    @GET("carts/1")
    fun getAllCart():Call<CartPojo>

    @GET("products/search")
    fun searchProducts(
        @Query("q") query: String
    ): Call<SearchPojo>

    @GET("products/category/smartphones")
    fun getSmartPhoneProducts():Call<CategoryListPojo>

    @GET("products/category/laptops")
    fun getLaptopsProducts():Call<CategoryListPojo>

    @GET("products/category/fragrances")
    fun getFragrancesProducts():Call<CategoryListPojo>

    @GET("products/category/skincare")
    fun getSkincareProducts():Call<CategoryListPojo>

    @GET("products/category/groceries")
    fun getGroceriesProducts():Call<CategoryListPojo>

    @GET("products/category/home-decoration")
    fun getHomeDecorationProducts():Call<CategoryListPojo>

    @GET("products/category/furniture")
    fun getFurnitureProducts():Call<CategoryListPojo>

    @GET("products/category/tops")
    fun getTopsProducts():Call<CategoryListPojo>

    @GET("products/category/womens-dresses")
    fun getWomenDressProducts():Call<CategoryListPojo>

    @GET("products/category/womens-shoes")
    fun getWomenShoesProducts():Call<CategoryListPojo>

    @GET("products/category/mens-shirts")
    fun getMenShirtsProducts():Call<CategoryListPojo>

    @GET("products/category/mens-shoes")
    fun getMenShoesProducts():Call<CategoryListPojo>

    @GET("products/category/mens-watches")
    fun getMenWatchesProducts():Call<CategoryListPojo>

    @GET("products/category/womens-watches")
    fun getWomenWatchesProducts():Call<CategoryListPojo>

    @GET("products/category/womens-bags")
    fun getWomenBagsProducts():Call<CategoryListPojo>

    @GET("products/category/womens-jewellery")
    fun getWomenJewelleryProducts():Call<CategoryListPojo>

    @GET("products/category/sunglasses")
    fun getSunglassesProducts():Call<CategoryListPojo>

    @GET("products/category/automotive")
    fun getAutoMativeProducts():Call<CategoryListPojo>

    @GET("products/category/motorcycle")
    fun getMotorCycleProducts():Call<CategoryListPojo>

    @GET("products/category/lighting")
    fun getLightingProducts():Call<CategoryListPojo>


}