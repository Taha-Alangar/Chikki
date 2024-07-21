package com.tahaalangar.paniwalaadmin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.tahaalangar.paniwalaadmin.adapter.AllProductsAdapter
import com.tahaalangar.paniwalaadmin.databinding.ActivityAllProductBinding
import com.tahaalangar.paniwalaadmin.pojos.Products

class AllProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAllProductBinding
    private val db = Firebase.firestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AllProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.RVAllProduct
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchProducts()
    }
    override fun onResume() {
        super.onResume()
        fetchProducts()
    }
    private fun fetchProducts() {
        db.collection("products")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val products = querySnapshot.toObjects(Products::class.java)
                Log.d("AllProductActivity", "Fetched products: $products")
                adapter = AllProductsAdapter(products.toMutableList()) { product ->
                    val intent = Intent(this, EditProductActivity::class.java)
                    intent.putExtra("product", product)
                    startActivity(intent)
                }
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error fetching products: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
    fun removeProduct(position: Int) {
        adapter.removeItem(position)
    }
}