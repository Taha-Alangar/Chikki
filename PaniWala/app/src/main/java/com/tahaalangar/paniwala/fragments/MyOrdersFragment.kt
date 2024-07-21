package com.tahaalangar.paniwala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.adapter.OrdersAdapter
import com.tahaalangar.paniwala.databinding.FragmentMyOrdersBinding
import com.tahaalangar.paniwala.pojo.Order

class MyOrdersFragment : Fragment() {
    private lateinit var binding:FragmentMyOrdersBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var ordersAdapter: OrdersAdapter
    private var orderList: MutableList<Order> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding=FragmentMyOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        setupRecyclerView()
        loadOrders()
    }
    private fun setupRecyclerView() {
        ordersAdapter = OrdersAdapter(requireContext(), orderList, childFragmentManager)
        binding.RVOrders.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ordersAdapter
        }
        
    }
    private fun loadOrders() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("orders")
                .whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { result ->
                    orderList.clear()
                    for (document in result) {
                        val order = document.toObject(Order::class.java)
                        order.id = document.id
                        orderList.add(order)
                    }
                    ordersAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to load orders", Toast.LENGTH_SHORT).show()
                }
        }
    }
}