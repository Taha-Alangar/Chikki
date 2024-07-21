package com.tahaalangar.paniwala.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.adapter.OrderDetailAdapter
import com.tahaalangar.paniwala.databinding.FragmentOrdeDetailBinding
import com.tahaalangar.paniwala.pojo.Order
import com.tahaalangar.paniwala.pojo.Products


class OrdeDetailFragment : Fragment() {

    private lateinit var binding: FragmentOrdeDetailBinding
    private lateinit var orderDetailAdapter: OrderDetailAdapter
    private var productList: MutableList<Order> = mutableListOf()
    private lateinit var db: FirebaseFirestore
    private var orderId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdeDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        orderId = arguments?.getString("orderId")
        orderId?.let {
            loadOrderDetails(it)
        } ?: run {
            Toast.makeText(requireContext(), "No Order ID found", Toast.LENGTH_SHORT).show()
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        orderDetailAdapter = OrderDetailAdapter(requireContext(), productList, parentFragmentManager)
        binding.rvOrderProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderDetailAdapter
        }
    }

    private fun loadOrderDetails(orderId: String) {
        db.collection("orders").document(orderId)
            .get()
            .addOnSuccessListener { document ->
                document?.let {
                    val order = it.toObject(Order::class.java)
                    order?.let { displayOrderDetails(it) }
                } ?: run {
                    Toast.makeText(requireContext(), "Order not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to load order details", Toast.LENGTH_SHORT).show()
            }
    }

    private fun displayOrderDetails(order: Order) {
        binding.tvOrderDetailOrderId.text = order.id
        binding.tvOrderDetailAddress.text = order.address
        binding.tvOrderDetailDeliveryTime.text = order.deliveryTime
        binding.tvOrderDetailStatus.text = order.status
    }
}