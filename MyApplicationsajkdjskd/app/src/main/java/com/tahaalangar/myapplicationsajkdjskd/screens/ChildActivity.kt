package com.tahaalangar.myapplicationsajkdjskd.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.myapplicationsajkdjskd.ChildCategoryItem
import com.tahaalangar.myapplicationsajkdjskd.R
import com.tahaalangar.myapplicationsajkdjskd.RetrofitInstance
import com.tahaalangar.myapplicationsajkdjskd.adapter.ChildAdapter
import com.tahaalangar.myapplicationsajkdjskd.databinding.ActivityChildBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChildBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        fetchChildCategory()

   }
    private fun setupRecyclerView() {
        binding.childRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchChildCategory() {
        val parentId = intent.getIntExtra("PARENT_ID", 0) // Retrieve the parent ID from the Intent
        val call = RetrofitInstance.apiInterface.getMemoProjectsChildCategoryList(parentId)
        call.enqueue(object : Callback<List<ChildCategoryItem>> {
            override fun onResponse(call: Call<List<ChildCategoryItem>>, response: Response<List<ChildCategoryItem>>) {
                if (response.isSuccessful) {
                    val childCategoryList = response.body()
                    childCategoryList?.let {
                        Log.d("ChildActivity", "Data fetched successfully")
                        binding.childRv.adapter = ChildAdapter(this@ChildActivity, it)
                    } ?: run {
                        Log.e("ChildActivity", "No data available")
                    }
                } else {
                    Log.e("ChildActivity", "Response unsuccessful: ${response.code()} ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ChildCategoryItem>>, t: Throwable) {
                Log.e("ChildActivity", "API call failed: ${t.message}")
            }
        })
    }
}