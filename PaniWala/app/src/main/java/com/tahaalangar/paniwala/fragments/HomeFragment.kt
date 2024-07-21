package com.tahaalangar.paniwala.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.LoginActivity
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.adapter.ProductsAdapter
import com.tahaalangar.paniwala.databinding.FragmentHomeBinding
import com.tahaalangar.paniwala.pojo.Products

class HomeFragment : Fragment() {
private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var productAdapter: ProductsAdapter
    private var productList: MutableList<Products> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        // Initialize GoogleSignInClient
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)


        setupRecyclerView()
        loadProducts()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductsAdapter(requireContext(),productList,parentFragmentManager )
        binding.RVAllProduct.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = productAdapter
        }
    }

    private fun loadProducts() {
        db.collection("products").get()
            .addOnSuccessListener { result ->
                productList.clear()
                for (document in result) {
                    val product = document.toObject(Products::class.java)
                    product.productId = document.id
                    productList.add(product)
                }
                productAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), exception.message, Toast.LENGTH_SHORT).show()
            }
    }

}