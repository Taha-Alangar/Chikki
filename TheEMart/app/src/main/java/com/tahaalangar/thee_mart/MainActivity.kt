package com.tahaalangar.thee_mart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.thee_mart.adapters.CategoryListAdapter
import com.tahaalangar.thee_mart.adapters.HomeProductListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var home_product_recyclerViews: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.category_recyclerView)
        home_product_recyclerViews = findViewById(R.id.home_product_recyclerview)
        progressBar=findViewById(R.id.progressBar2)

        getCategoryList()
        getAllProduct()
    }

    private fun getAllProduct() {
        progressBar.visibility=View.VISIBLE
        RetrofitInstance.apiInterface.getAllProducts().enqueue(object : Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                if (response.code()==200 && response.body()!=null){
                    val gridLayoutManager=GridLayoutManager(this@MainActivity,2)
                    home_product_recyclerViews.layoutManager=gridLayoutManager

                    val adapter=HomeProductListAdapter(response.body()!!,this@MainActivity)
                    home_product_recyclerViews.adapter=adapter
                    progressBar.visibility=View.GONE
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d("test",t.message.toString())
                progressBar.visibility=View.GONE

            }
        })
    }

    private fun getCategoryList() {
        RetrofitInstance.apiInterface.getCategoryList().enqueue(object : Callback<CategoryLists?> {
            override fun onResponse(call: Call<CategoryLists?>, response: Response<CategoryLists?>) {
                if (response.code()==200 && response.body()!=null){
                    val linearLayoutManager= LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                    recyclerView.layoutManager=linearLayoutManager

                    val adapter=CategoryListAdapter(response.body()!!,this@MainActivity)
                    recyclerView.adapter=adapter

                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryLists?>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                Log.d("test",t.message.toString())
            }
        })
    }
}