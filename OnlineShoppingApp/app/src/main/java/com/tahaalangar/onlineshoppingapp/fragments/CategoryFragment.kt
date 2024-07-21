package com.tahaalangar.onlineshoppingapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.onlineshoppingapp.adapters.Category_Detail_List_Adapter
import com.tahaalangar.onlineshoppingapp.databinding.FragmentCategoryBinding
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListPojo
import com.tahaalangar.onlineshoppingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var recyclerView_categoryList: RecyclerView
    private lateinit var data: TextView

    private lateinit var progressBar_categoryDetailList: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCategoryBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_categoryList=binding.categoryListRecyclerView
        data=binding.categoryName

        progressBar_categoryDetailList=binding.progressBarCategoryDetailList

        val category = arguments?.getString("category")
        category?.let { getCategoryProducts(it) }

        data.text=category


    }
    private fun getCategoryProducts(category: String) {
        progressBar_categoryDetailList.visibility=View.VISIBLE
        val call: Call<CategoryListPojo>? = when (category) {
            "smartphones" -> RetrofitInstance.apiInterface.getSmartPhoneProducts()
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

        call?.enqueue(object : Callback<CategoryListPojo?> {
            override fun onResponse(call: Call<CategoryListPojo?>, response: Response<CategoryListPojo?>) {
                progressBar_categoryDetailList.visibility=View.GONE
                if (response.code() == 200 && response.body() != null) {
                    val gridLayoutManager = GridLayoutManager(requireContext(), 2)
                    recyclerView_categoryList.layoutManager = gridLayoutManager


                    val adapter = Category_Detail_List_Adapter(response.body()!!, requireContext())
                    recyclerView_categoryList.adapter = adapter

                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryListPojo?>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.d("test", t.message.toString())
                progressBar_categoryDetailList.visibility=View.VISIBLE
            }
        })
    }

}