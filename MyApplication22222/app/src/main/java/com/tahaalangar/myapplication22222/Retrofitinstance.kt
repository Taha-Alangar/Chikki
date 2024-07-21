package com.tahaalangar.myapplication22222

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://mapi.trycatchtech.com/v3/virat_kohli/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    val apiInterface by lazy {
        retrofit.create(Newsinterface::class.java)
    }
}