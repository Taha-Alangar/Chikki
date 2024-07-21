package com.tahaalangar.thee_mart

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.thee_mart.adapters.HomeProductListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetail : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private lateinit var productDetailRecyclerView: RecyclerView
    private lateinit var data:TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        data=findViewById(R.id.data)
        progressBar=findViewById(R.id.progressBar)

        val category = intent.getStringExtra("category")
        category?.let {
            getCategoryProducts(it)
        }
        data.text=category

        productDetailRecyclerView=findViewById(R.id.productDetailRecyclerView)

//        getSmartPhone()
//        getLaptops()

    }

    private fun getCategoryProducts(category: String) {
        progressBar.visibility=View.VISIBLE

        val call:Call<Products> ? =when(category){
            "smartphones"->RetrofitInstance.apiInterface.getSmartPhoneProducts()
            "laptops" -> RetrofitInstance.apiInterface.getLaptopsProducts()
            "fragrances" -> RetrofitInstance.apiInterface.getFragrancesProducts()
            "skincare" -> RetrofitInstance.apiInterface.getSkincareProducts()
            "groceries" -> RetrofitInstance.apiInterface.getGroceriesProducts()
            "home-decoration" -> RetrofitInstance.apiInterface.getHomeDecorationProducts()
            "furniture" -> RetrofitInstance.apiInterface.getFurnitureProducts()
            "tops" -> RetrofitInstance.apiInterface.getTopsProducts()
            "womens-dresses" -> RetrofitInstance.apiInterface.getWomenDressProducts()
            "womens-shoes" -> RetrofitInstance.apiInterface.getWomenShoesProducts()
            "mens-shirts" -> RetrofitInstance.apiInterface.getMenShirtsProducts()
            "mens-shoes" -> RetrofitInstance.apiInterface.getMenShoesProducts()
            "mens-watches" -> RetrofitInstance.apiInterface.getMenWatchesProducts()
            "womens-watches" -> RetrofitInstance.apiInterface.getWomenWatchesProducts()
            "womens-bags" -> RetrofitInstance.apiInterface.getWomenBagsProducts()
            "womens-jewellery" -> RetrofitInstance.apiInterface.getWomenJewelleryProducts()
            "sunglasses" -> RetrofitInstance.apiInterface.getSunglassesProducts()
            "automotive" -> RetrofitInstance.apiInterface.getAutoMativeProducts()
            "motorcycle" -> RetrofitInstance.apiInterface.getMotorCycleProducts()
            "lighting" -> RetrofitInstance.apiInterface.getLightingProducts()
            else -> null
        }
        call?.enqueue(object : Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                if (response.code()==200 && response.body()!=null){
                    val gridLayoutManager= GridLayoutManager(this@ProductDetail,2)
                    productDetailRecyclerView.layoutManager=gridLayoutManager


                    val adapter= HomeProductListAdapter(response.body()!!,this@ProductDetail)
                    productDetailRecyclerView.adapter=adapter

                    progressBar.visibility=View.GONE

                    Toast.makeText(this@ProductDetail, "Success", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@ProductDetail, "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {

                Toast.makeText(this@ProductDetail, t.message, Toast.LENGTH_SHORT).show()
                Log.d("test",t.message.toString())

                progressBar.visibility=View.GONE

            }
        })
    }

}