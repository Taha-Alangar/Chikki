package com.tahaalangar.memoprojects.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.memoprojects.adapter.ProjectCategoryListAdapter
import com.tahaalangar.memoprojects.databinding.FragmentProjectCategoryListBinding
import com.tahaalangar.memoprojects.databinding.FragmentProjectListBinding
import com.tahaalangar.memoprojects.pojos.ChildCategoryItem
import com.tahaalangar.memoprojects.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectCategoryListFragment : Fragment() {
   private lateinit var binding:FragmentProjectCategoryListBinding
    private var parentId: Int = 0
    private var parentName: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentProjectCategoryListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the parent ID from the arguments
        parentId = arguments?.getInt("PARENT_ID") ?: 0
        parentName = arguments?.getString("PARENT_NAME") ?: ""
        binding.projectName.text = parentName
        setupRecyclerView()
        fetchChildCategory()

        binding.back.setOnClickListener {
            activity?.onBackPressed()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun setupRecyclerView() {
        binding.projectCategoryListRV.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun fetchChildCategory() {
        binding.progressBar.visibility = View.VISIBLE
        val call = RetrofitInstance.apiInterface.getMemoProjectsChildCategoryList(parentId)
        call.enqueue(object : Callback<List<ChildCategoryItem>> {
            override fun onResponse(call: Call<List<ChildCategoryItem>>, response: Response<List<ChildCategoryItem>>) {
                if (response.isSuccessful) {
                    val childCategoryList = response.body()
                    childCategoryList?.let {
                        Log.d("ProjectListFragment", "Data fetched successfully")
                        binding.projectCategoryListRV.adapter = ProjectCategoryListAdapter(requireContext(), it)
                        binding.progressBar.visibility = View.GONE
                    } ?: run {
                        Log.e("ProjectListFragment", "No data available")
                    }
                } else {
                    Log.e("ProjectListFragment", "Response unsuccessful: ${response.code()} ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ChildCategoryItem>>, t: Throwable) {
                Log.e("ProjectListFragment", "API call failed: ${t.message}")
            }
        })
    }

}