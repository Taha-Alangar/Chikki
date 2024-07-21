package com.tahaalangar.myapplicationsajkdjskd

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MemoProjectsApi {


    @GET("memo_projects_post_list")
    fun getMemoProjectsPostList(
        @Query("category_id") categoryId: Int
    ): Call<List<MemoProjectItem>>

    @GET("memo_projects_all_data")
    fun getMemoProjectsAllData(): Call<MemoProjectData>

    @GET("memo_projects_child_category_list")
    fun getMemoProjectsChildCategoryList(
        @Query("parent_id") parentId: Int
    ): Call<List<ChildCategoryItem>>

    @GET("memo_projects_parent_category_list")
    fun getMemoProjectsParentCategoryList(): Call<List<ParentCategoryItem>>
}