package com.tahaalangar.paniwalaadmin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwalaadmin.adapter.ViewOrdersAdapter
import com.tahaalangar.paniwalaadmin.databinding.ActivityViewOrderBinding
import com.tahaalangar.paniwalaadmin.pojos.Order

class ViewOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewOrderBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var ordersAdapter: ViewOrdersAdapter
        private var orderList: MutableList<Order> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityViewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        loadOrders()
    }
    private fun setupRecyclerView() {
        ordersAdapter = ViewOrdersAdapter(this, orderList) { order, newStatus ->
            updateOrderStatus(order, newStatus)
        }
        binding.RVViewOrders.apply {
            layoutManager = LinearLayoutManager(this@ViewOrderActivity)
            adapter = ordersAdapter
        }
    }
    private fun loadOrders() {
        db.collection("orders")
            .get()
            .addOnSuccessListener { result ->
                orderList.clear()
                for (document in result) {
                    val order = document.toObject(Order::class.java)
                    order.id = document.id
                    // Fetch user name based on userId from users collection
                    db.collection("users").document(order.userId)
                        .get()
                        .addOnSuccessListener { userDoc ->
                            if (userDoc.exists()) {
                                val userEmail = userDoc.getString("email") ?: ""
                                order.userEmail = userEmail
                                orderList.add(order)
                                orderList.sortBy { it.status } // Sort orders by status: pending first, delivered last
                                ordersAdapter.notifyDataSetChanged()
                            } else {
                                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener { exception ->
                            Toast.makeText(this, "Failed to fetch user", Toast.LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to load orders", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateOrderStatus(order: Order, newStatus: String) {
        db.collection("orders").document(order.id)
            .update("status", newStatus)
            .addOnSuccessListener {
                Toast.makeText(this, "Order status updated", Toast.LENGTH_SHORT).show()
                loadOrders()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to update order status", Toast.LENGTH_SHORT).show()
            }
    }
}