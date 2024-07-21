package com.tahaalangar.onlineshoppingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.onlineshoppingapp.adapters.SearchListAdapter
import com.tahaalangar.onlineshoppingapp.databinding.FragmentSearchFragmentsBinding
import com.tahaalangar.onlineshoppingapp.pojos.SearchPojo
import com.tahaalangar.onlineshoppingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragments : Fragment() {

    private lateinit var binding: FragmentSearchFragmentsBinding
    private lateinit var recyclerView_searchList: RecyclerView
    private lateinit var noItemsFoundText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSearchFragmentsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_searchList = binding.searchRecyclerView
        noItemsFoundText = binding.noItemsFoundText

        val query = arguments?.getString("query") ?: ""
        if (query.isNotBlank()) {
            searchProducts(query)
        } else {
            Toast.makeText(requireContext(), "No search query provided", Toast.LENGTH_SHORT).show()
        }
    }
    private fun searchProducts(query: String) {
        RetrofitInstance.apiInterface.searchProducts(query).enqueue(object : Callback<SearchPojo?> {
            override fun onResponse(call: Call<SearchPojo?>, response: Response<SearchPojo?>) {
                if (response.isSuccessful && response.body() != null) {
                    val products = response.body()!!.products
                    if (products.isNotEmpty()) {
                        noItemsFoundText.visibility = View.GONE
                        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
                        recyclerView_searchList.layoutManager = gridLayoutManager

                        val adapter = SearchListAdapter(response.body()!!, requireContext())
                        recyclerView_searchList.adapter = adapter
                    } else {
                        noItemsFoundText.visibility = View.VISIBLE
                    }
                } else {
                    noItemsFoundText.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<SearchPojo?>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}