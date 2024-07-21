package com.tahaalangar.mobileshoppingapi

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products/category/smartphones")
    fun getSmartPhoneData():Call<Products>

    @GET("products/category/laptops")
    fun getLaptopData():Call<Products>

    @GET("products/category/groceries")
    fun getGroceriesData():Call<Products>

}