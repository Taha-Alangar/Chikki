package com.tahaalangar.googleloginfirebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tahaalangar.googleloginfirebase.databinding.ActivityDataListBinding

class DataListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataListBinding
    private var db= Firebase.firestore
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDataListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView=binding.rvProductList
        recyclerView.adapter=ProductAdapter(emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)

        db= FirebaseFirestore.getInstance()
        db.collection("products").get().addOnSuccessListener {
            val products=it.toObjects(Products::class.java)
            recyclerView.adapter=ProductAdapter(products)
                }.addOnFailureListener {
            Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
        }
    }
}