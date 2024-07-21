package com.tahaalangar.memoprojects.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.adapter.CategoryListAdapter
import com.tahaalangar.memoprojects.adapter.ProjectAdapter
import com.tahaalangar.memoprojects.databinding.FragmentHomeBinding
import com.tahaalangar.memoprojects.pojos.Categories
import com.tahaalangar.memoprojects.pojos.ParentCategoryItem
import com.tahaalangar.memoprojects.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryList = listOf(
            Categories(R.drawable.android_logo, "Android"),
            Categories(R.drawable.ios_52, "IOS"),
            Categories(R.drawable.artificial_intelligence, "Artificial Intelligence"),
            Categories(R.drawable.python_snake, "Python"),
            Categories(R.drawable.php, "PHP"),
            Categories(R.drawable.mining, "Data Mining"),
            Categories(R.drawable.biometric_identification, "Biometric & Smart Card"),
            Categories(R.drawable.microsoft_dot_net, "Dot Net"),

            Categories(R.drawable.wireless_communication, "Wireless Communication"),
            Categories(R.drawable.robototics, "Robotics"),
            Categories(R.drawable.iot, "IOT"),
            Categories(R.drawable.sensors, "Sensor"),
            Categories(R.drawable.microcontroller, "8052 Microcontroller"),
            Categories(R.drawable.pic_microcontroller, "PIC Microcontroller"),
            Categories(R.drawable.avr_mcrocontroller, "AVR Microcontroller"),
            Categories(R.drawable.gps, "GPS"),
            Categories(R.drawable.electrical, "Electrical"),

            Categories(R.drawable.mechtronics, "Mechatronics"),
            Categories(R.drawable.mechanical_major_project, "Mechanical Major Project"),
            Categories(R.drawable.mechanical_mini_projects, "Mechanical Mini Project"),
            Categories(R.drawable.mechanical_design, "Mechanical Design"),

        )

        binding.editTextSearch.setOnClickListener {
            val searchFragment = SearchFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame_container, searchFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // Initialize the RecyclerView
        binding.categoriesRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.categoriesRV.adapter = CategoryListAdapter(categoryList)

        setupRecyclerView()
        fetchParentCategory()

    }
    private fun setupRecyclerView() {
        binding.projectsRV.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
    }

    private fun fetchParentCategory() {
        binding.homeProgressBar.visibility = View.VISIBLE
        val call = RetrofitInstance.apiInterface.getMemoProjectsParentCategoryList()
        call.enqueue(object : Callback<List<ParentCategoryItem>> {
            override fun onResponse(call: Call<List<ParentCategoryItem>>, response: Response<List<ParentCategoryItem>>) {
                if (isAdded){

                if (response.isSuccessful) {

                    val parentCategoryList = response.body()
                    parentCategoryList?.let {
                        Log.d("ParentActivity", "Data fetched successfully")
                        binding.projectsRV.adapter = ProjectAdapter(requireContext(), it)
                        binding.homeProgressBar.visibility = View.GONE
                    } ?: run {
                        Log.e("ParentActivity", "No data available")
                    }
                } else {
                    Log.e("ParentActivity", "Response unsuccessful: ${response.errorBody()}")
                }
            }
            }

            override fun onFailure(call: Call<List<ParentCategoryItem>>, t: Throwable) {
                if (isAdded) {
                    Log.e("HomeFragment", "API call failed: ${t.message}")
                }
            }
        })
    }
}