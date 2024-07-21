package com.tahaalangar.myapplicationsajkdjskd.screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.tahaalangar.myapplicationsajkdjskd.MemoProjectItem
import com.tahaalangar.myapplicationsajkdjskd.R
import com.tahaalangar.myapplicationsajkdjskd.RetrofitInstance
import com.tahaalangar.myapplicationsajkdjskd.adapter.ChildAdapter
import com.tahaalangar.myapplicationsajkdjskd.adapter.ChildDetailAdapter
import com.tahaalangar.myapplicationsajkdjskd.databinding.ActivityChildDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChildDetailBinding
    private lateinit var categoryAdapter: ChildAdapter
    private lateinit var postAdapter: ChildDetailAdapter
    private var selectedCategoryId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        fetchChildDetail()
    }

    private fun setupRecyclerView() {
        binding.childDetailRv.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchChildDetail() {
        val categoryId = intent.getIntExtra("CATEGORY_ID", 0)
        val call = RetrofitInstance.apiInterface.getMemoProjectsPostList(categoryId)
        call.enqueue(object : Callback<List<MemoProjectItem>> {
            override fun onResponse(call: Call<List<MemoProjectItem>>, response: Response<List<MemoProjectItem>>): Unit = with(response) {
                if (response.isSuccessful) {
                    val memoProjectList = response.body()
                    memoProjectList?.let {
                        Log.d("ChildDetailActivity", "Data fetched successfully")
                        showPosts(it)
                    } ?: run {
                        Log.e("ChildDetailActivity", "No data available")
                        showNoDataPlaceholder()
                    }
                } else {
                    Log.e("ChildDetailActivity", "Response unsuccessful: ${response.code()} ${response.errorBody()?.string()}")
                    showNoDataPlaceholder()
                }
            }

            override fun onFailure(call: Call<List<MemoProjectItem>>, t: Throwable) {
                Log.e("ChildDetailActivity", "API call failed: ${t.message}")
                showNoDataPlaceholder()
            }
        })
    }
    private fun showPosts(posts: List<MemoProjectItem>) {
        binding.childDetailRv.visibility = View.VISIBLE
        binding.noDataImageView.visibility = View.GONE
        postAdapter = ChildDetailAdapter(this@ChildDetailActivity, posts)
        binding.childDetailRv.adapter = postAdapter
    }

    private fun showNoDataPlaceholder() {
        binding.childDetailRv.visibility = View.GONE
        binding.noDataImageView.visibility = View.VISIBLE
        Picasso.get().load(R.drawable.no_data_found).into(binding.noDataImageView)
    }

}

