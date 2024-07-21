package com.tahaalangar.memoprojects.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.adapter.AllProjectsAdapter
import com.tahaalangar.memoprojects.adapter.CategoryListAdapter
import com.tahaalangar.memoprojects.adapter.ProjectAdapter
import com.tahaalangar.memoprojects.databinding.FragmentCartBinding
import com.tahaalangar.memoprojects.pojos.AllCategory
import com.tahaalangar.memoprojects.pojos.MemoProjectData
import com.tahaalangar.memoprojects.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}