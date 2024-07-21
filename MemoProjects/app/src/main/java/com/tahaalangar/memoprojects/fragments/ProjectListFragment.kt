package com.tahaalangar.memoprojects.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.adapter.ProjectAdapter
import com.tahaalangar.memoprojects.adapter.ProjectCategoryListAdapter
import com.tahaalangar.memoprojects.adapter.ProjectListAdapter
import com.tahaalangar.memoprojects.databinding.FragmentProjectListBinding
import com.tahaalangar.memoprojects.pojos.ChildCategoryItem
import com.tahaalangar.memoprojects.pojos.MemoProjectItem
import com.tahaalangar.memoprojects.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectListFragment : Fragment() {
    private lateinit var binding: FragmentProjectListBinding
    private var categoryId: Int = 0
    private var categoryName: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProjectListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            activity?.onBackPressed()
            activity?.supportFragmentManager?.popBackStack()
        }

        // Retrieve the parent ID from the arguments
        categoryId = arguments?.getInt("CATEGORY_ID") ?: 0
        categoryName = arguments?.getString("CATEGORY_NAME") ?: ""
        binding.projectListName.text = categoryName
        setupRecyclerView()
        fetchProjectList()
    }

    private fun setupRecyclerView() {
        binding.projectListRV.layoutManager = GridLayoutManager(requireContext(),2)
    }

    private fun fetchProjectList() {
        binding.progressBar3.visibility = View.VISIBLE
        val call = RetrofitInstance.apiInterface.getMemoProjectsPostList(categoryId)
        call.enqueue(object : Callback<List<MemoProjectItem>> {
            override fun onResponse(
                call: Call<List<MemoProjectItem>>,
                response: Response<List<MemoProjectItem>>
            ): Unit = with(response) {
                if (response.isSuccessful) {
                    val memoProjectList = response.body()
                    memoProjectList?.let {
                        Log.d("ChildDetailActivity", "Data fetched successfully")
                        showPosts(it)
                        binding.progressBar3.visibility = View.GONE
                    } ?: run {
                        Log.e("ChildDetailActivity", "No data available")
                        showNoDataPlaceholder()

                    }
                } else {
                    Log.e(
                        "ChildDetailActivity",
                        "Response unsuccessful: ${response.code()} ${
                            response.errorBody()?.string()
                        }"
                    )
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
        binding.projectListRV.visibility = View.VISIBLE
        binding.noDataFoundImage.visibility = View.GONE
        binding.projectListRV.adapter = ProjectListAdapter(requireContext(), posts)
    }
    private fun showNoDataPlaceholder() {
        binding.projectListRV.visibility = View.GONE
        binding.noDataFoundImage.visibility = View.VISIBLE
        Picasso.get().load(R.drawable.no_data_found).into(binding.noDataFoundImage)
    }
}