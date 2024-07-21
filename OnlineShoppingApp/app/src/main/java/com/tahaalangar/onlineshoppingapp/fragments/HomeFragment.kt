package com.tahaalangar.onlineshoppingapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.adapters.BestSeller_Adapter
import com.tahaalangar.onlineshoppingapp.adapters.CategoryList_Apater
import com.tahaalangar.onlineshoppingapp.databinding.FragmentHomeFragmentsBinding
import com.tahaalangar.onlineshoppingapp.pojos.AllProduct
import com.tahaalangar.onlineshoppingapp.pojos.CategoryList
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListImages
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListPojo
import com.tahaalangar.onlineshoppingapp.retrofit.RetrofitInstance
import com.tahaalangar.onlineshoppingapp.screens.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeFragmentsBinding
    private lateinit var recyclerView_category: RecyclerView
    private lateinit var recyclerView_bestSeller: RecyclerView

    val categoryImages= listOf(
        CategoryListImages(R.drawable.beauty),
        CategoryListImages(R.drawable.fragnance_img),
        CategoryListImages(R.drawable.furniture),
        CategoryListImages(R.drawable.grocery),
        CategoryListImages(R.drawable.home_decoration_img),
        CategoryListImages(R.drawable.kitchen),
        CategoryListImages(R.drawable.laptop),
        CategoryListImages(R.drawable.cloth),
        CategoryListImages(R.drawable.shoes),
        CategoryListImages(R.drawable.watch),
        CategoryListImages(R.drawable.mobile_accersoires),
        CategoryListImages(R.drawable.motorbike),
        CategoryListImages(R.drawable.products),
        CategoryListImages(R.drawable.smartphones),
        CategoryListImages(R.drawable.physical),
        CategoryListImages(R.drawable.sunglasses),
        CategoryListImages(R.drawable.tablet),
        CategoryListImages(R.drawable.womens_tops),
        CategoryListImages(R.drawable.vehicle),
        CategoryListImages(R.drawable.handbag),
        CategoryListImages(R.drawable.dress),
        CategoryListImages(R.drawable.jewelry),
        CategoryListImages(R.drawable.womens_shoes),
        CategoryListImages(R.drawable.women_wristwatch)
    )
    private lateinit var progressBar_category: ProgressBar
    private lateinit var progressBar_bestSeller: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeFragmentsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_category = binding.recylerViewCategory
        recyclerView_bestSeller = binding.recyclerViewBestSellerProducts

        progressBar_category=binding.progressBarCategory
        progressBar_bestSeller=binding.progressBarBestSeller

        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel(R.drawable.banner_1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner_3, ScaleTypes.FIT))

        val imageSlider=binding.imageSlider
        imageSlider.setImageList(imageList,ScaleTypes.FIT)
        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage="Selected image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
                // You can listen here.
            }
            override fun doubleClick(position: Int) {
                // Do not use onItemSelected if you are using a double click listener at the same time.
                // Its just added for specific cases.
                // Listen for clicks under 250 milliseconds.
            } })

        getCategoryList()
        getAllProduct()

        binding.editTextSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString()
            if (query.isNotBlank()) {
                (activity as? MainActivity)?.navigateToSearchFragment(query)
            }
        }
    }
    private fun getAllProduct() {
        progressBar_bestSeller.visibility=View.VISIBLE
        RetrofitInstance.apiInterface.getAllProducts().enqueue(object : Callback<AllProduct?> {
            override fun onResponse(call: Call<AllProduct?>, response: Response<AllProduct?>) {
                if (!isAdded) return // Ensure the fragment is attached
                if (response.code() == 200 && response.body() != null) {
                    val gridLayoutManager = GridLayoutManager(requireContext(), 2)
                    recyclerView_bestSeller.layoutManager = gridLayoutManager

                    val adapter = BestSeller_Adapter(response.body()!!, requireContext())
                    recyclerView_bestSeller.adapter = adapter
                    progressBar_bestSeller.visibility=View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<AllProduct?>, t: Throwable) {
                if (!isAdded) return // Ensure the fragment is attached
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.d("test", t.message.toString())
                progressBar_bestSeller.visibility=View.VISIBLE
            }
        })
    }

    private fun getCategoryList() {
        progressBar_category.visibility = View.VISIBLE
        RetrofitInstance.apiInterface.getCategoryList().enqueue(object : Callback<CategoryList?> {
            override fun onResponse(call: Call<CategoryList?>, response: Response<CategoryList?>) {
                if (!isAdded) return // Ensure the fragment is attached
                progressBar_category.visibility = View.GONE
                if (response.code() == 200 && response.body() != null) {
                    val linearLayoutManager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    recyclerView_category.layoutManager = linearLayoutManager

                    val adapter = CategoryList_Apater(response.body()!!, categoryImages, requireContext()) { category ->
                        onClickCategory(category)
                    }
                    recyclerView_category.adapter = adapter
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CategoryList?>, t: Throwable) {
                if (!isAdded) return // Ensure the fragment is attached
                progressBar_category.visibility = View.GONE
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
                Log.d("test", t.message.toString())
            }
        })
    }
    private fun onClickCategory(category: String) {
        (activity as? OnCategorySelectedListener)?.onCategorySelected(category)
    }
}
interface OnCategorySelectedListener {
    fun onCategorySelected(category: String)
}