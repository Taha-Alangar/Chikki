package com.trycatchprojects.maganlalchikki.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://appy.trycatchtech.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MaganlalChikkiApi by lazy {
        retrofit.create(MaganlalChikkiApi::class.java)
    }
}