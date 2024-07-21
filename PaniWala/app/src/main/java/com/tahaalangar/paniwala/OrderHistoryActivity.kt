    package com.tahaalangar.paniwala

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.adapter.OrdersAdapter
import com.tahaalangar.paniwala.databinding.ActivityOrderHistoryBinding
import com.tahaalangar.paniwala.pojo.Order

    class OrderHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOrderHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}