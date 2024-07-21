package com.tahaalangar.myapplication22222

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Newsinterface {

    @GET("virat_kohli_news_list")
    fun getNewsList(): Call<List<newItem>>

    @GET("virat_kohli_single_news")
    fun getNewsById(@Query("news_id") id: String): Call<newsById>

}