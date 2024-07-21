package com.tahaalangar.myapplication22222

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.myapplication22222.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newsRV.layoutManager = LinearLayoutManager(this)
        fetchNews()
    }

    private fun fetchNews() {
        val call = RetrofitInstance.apiInterface.getNewsList()
        call.enqueue(object : Callback<List<newItem>> {
            override fun onResponse(call: Call<List<newItem>>, response: Response<List<newItem>>) {
                if (response.isSuccessful) {
                    val newsList = response.body()
                    if (newsList != null) {
                        binding.newsRV.adapter = NewsAdapter(newsList) {
                            val intent = Intent(this@MainActivity, MainActivity2::class.java)
                            intent.putExtra("newsId", it.id)
                            startActivity(intent)
                        }
                    }
                } else {
                    Log.e("MainActivity", "Response not successful: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<List<newItem>>, t: Throwable) {
                Log.e("MainActivity", "Network request failed", t)
                Toast.makeText(this@MainActivity, "Failed to get news", Toast.LENGTH_SHORT).show()
            }
        })
    }
}