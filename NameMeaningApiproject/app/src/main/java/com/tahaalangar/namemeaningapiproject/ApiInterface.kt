package com.tahaalangar.namemeaningapiproject

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v1/naamkaran/post_list_by_cat_and_gender")
    fun getData(
        @Query("category_id") category_id:Int,
        @Query("gender") gender:Int,
    ): Call<category_and_gender>
}