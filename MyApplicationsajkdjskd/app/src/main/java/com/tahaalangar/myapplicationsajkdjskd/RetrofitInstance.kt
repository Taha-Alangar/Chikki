package com.tahaalangar.myapplicationsajkdjskd

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://mapi.trycatchtech.com/v3/memo_projects/").addConverterFactory(
            GsonConverterFactory.create()).build()
    }

    val apiInterface by lazy {
        retrofit.create(MemoProjectsApi::class.java)
    }
}