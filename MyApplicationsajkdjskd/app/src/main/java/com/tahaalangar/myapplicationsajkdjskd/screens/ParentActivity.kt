package com.tahaalangar.myapplicationsajkdjskd.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.myapplicationsajkdjskd.ParentCategoryItem
import com.tahaalangar.myapplicationsajkdjskd.RetrofitInstance
import com.tahaalangar.myapplicationsajkdjskd.adapter.ParentAdapter
import com.tahaalangar.myapplicationsajkdjskd.databinding.ActivityParentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParentActivity : AppCompatActivity() {
private lateinit var binding: ActivityParentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchParentCategory()


    }
    private fun setupRecyclerView() {
        binding.parentRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchParentCategory() {
        val call = RetrofitInstance.apiInterface.getMemoProjectsParentCategoryList()
        call.enqueue(object : Callback<List<ParentCategoryItem>> {
            override fun onResponse(call: Call<List<ParentCategoryItem>>, response: Response<List<ParentCategoryItem>>) {
                if (response.isSuccessful) {
                    val parentCategoryList = response.body()
                    parentCategoryList?.let {
                        Log.d("ParentActivity", "Data fetched successfully")
                        binding.parentRv.adapter = ParentAdapter(this@ParentActivity, it)
                    } ?: run {
                        Log.e("ParentActivity", "No data available")
                    }
                } else {
                    Log.e("ParentActivity", "Response unsuccessful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<ParentCategoryItem>>, t: Throwable) {
                Log.e("ParentActivity", "API call failed: ${t.message}")
            }
        })
    }

}