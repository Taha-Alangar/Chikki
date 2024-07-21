package com.tahaalangar.memoprojects.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.adapter.AllProjectsAdapter
import com.tahaalangar.memoprojects.databinding.FragmentProjectsBinding
import com.tahaalangar.memoprojects.pojos.MemoProjectData
import com.tahaalangar.memoprojects.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectsFragment : Fragment() {
private lateinit var binding: FragmentProjectsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentProjectsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the RecyclerView
        binding.projectsRV.layoutManager = GridLayoutManager(requireContext(),2)


        fetchAllProjects()
    }

    private fun fetchAllProjects(){
        binding.AllProjectprogressBar.visibility = View.VISIBLE
        val call = RetrofitInstance.apiInterface.getMemoProjectsAllData()
        call.enqueue(object : Callback<MemoProjectData> {
            override fun onResponse(call: Call<MemoProjectData>, response: Response<MemoProjectData>) {
                if (isAdded){

                if (response.isSuccessful) {
                    val allProjectsList = response.body()
                    allProjectsList?.let {
                        Log.d("ParentActivity", "Data fetched successfully")
                        binding.projectsRV.adapter = AllProjectsAdapter(requireContext(), it)
//                        {project->
//                            navigateToDetailFragment(project)
//                        }
                        binding.AllProjectprogressBar.visibility = View.GONE
                    }
                }
            }
            }
            override fun onFailure(call: Call<MemoProjectData   >, t: Throwable) {
                if (isAdded){

                    Log.e("ParentActivity", "API call failed: ${t.message}")
                }
            }
        })
    }


}