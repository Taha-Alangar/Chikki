package com.tahaalangar.paniwalaadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwalaadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var db: FirebaseFirestore
    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser==null){
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnSignOut.setOnClickListener {
            auth.signOut()
            binding.tvEmail.text=auth.currentUser?.email
            startActivity(Intent(this,LoginActivity::class.java))
            finish()

        }
        binding.btnAddProduct.setOnClickListener {
            startActivity(Intent(this,AddProductActivity::class.java))
        }
        binding.btnViewOrder.setOnClickListener {
            startActivity(Intent(this,ViewOrderActivity::class.java))
        }

        binding.btnAllProduct.setOnClickListener {
            startActivity(Intent(this,AllProductActivity::class.java))
        }

        // Load and display counts of pending and completed orders
        loadOrderCounts()
    }
    private fun loadOrderCounts() {
        val pendingOrdersRef = db.collection("orders").whereEqualTo("status", "Pending")
        val completedOrdersRef = db.collection("orders").whereEqualTo("status", "Delivered")

        pendingOrdersRef.get()
            .addOnSuccessListener { querySnapshot ->
                val pendingCount = querySnapshot.size()
                binding.tvPendingOrders.text = "$pendingCount"
            }
            .addOnFailureListener { exception ->
                // Handle failure to fetch pending orders
            }

        completedOrdersRef.get()
            .addOnSuccessListener { querySnapshot ->
                val completedCount = querySnapshot.size()
                binding.tvCompletedOrders.text = "$completedCount"
            }
            .addOnFailureListener { exception ->
                // Handle failure to fetch completed orders
            }
    }
    override fun onResume() {
        super.onResume()
        binding.tvEmail.text=auth.currentUser?.email
    }
}