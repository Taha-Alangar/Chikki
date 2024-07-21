package com.tahaalangar.myapplication22222

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso
import com.tahaalangar.myapplication22222.databinding.ActivityMain2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsId=intent.getStringExtra("newsId")
        if (newsId != null) {
            fetchNewsById(newsId)
        } else {
            Toast.makeText(this, "No news ID found", Toast.LENGTH_SHORT).show()
        }
    }


    private fun fetchNewsById(newsId: String) {
        val call = RetrofitInstance.apiInterface.getNewsById(newsId)
        call.enqueue(object : Callback<newsById> {
            override fun onResponse(call: Call<newsById>, response: Response<newsById>) {
                if (response.isSuccessful) {
                    val news = response.body()
                    if (news != null) {
                        binding.newsByIdTitle.text = news.news_title
                        binding.newsByIdDescription.text = news.news_desc
                        Picasso.get().load(news.news_image).into(binding.newsByIdImage)
                    }
                } else {
                    Log.e("MainActivity2", "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<newsById>, t: Throwable) {
                Log.e("MainActivity2", "Network request failed", t)
                Toast.makeText(this@MainActivity2, "Failed to get news", Toast.LENGTH_SHORT).show()
            }
        })
    }
}