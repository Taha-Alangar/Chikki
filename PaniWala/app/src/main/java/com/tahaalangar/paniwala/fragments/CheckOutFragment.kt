package com.tahaalangar.paniwala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tahaalangar.paniwala.TimeSlotDialogFragment
import com.tahaalangar.paniwala.databinding.FragmentCheckOutBinding
import com.tahaalangar.paniwala.pojo.CartItem
import com.tahaalangar.paniwala.pojo.Order
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


class CheckOutFragment : Fragment(), TimeSlotDialogFragment.OnTimeSlotSelectedListener {

    private lateinit var binding: FragmentCheckOutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var cartItemList: MutableList<CartItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckOutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        loadCartItems()

        binding.editAddress.setOnClickListener {
            binding.edtCheckOutAddress.isEnabled = true
            binding.editAddress.visibility = View.GONE
        }

        binding.tvCheckOutTimeSlot.setOnClickListener {
            val dialog = TimeSlotDialogFragment()
            dialog.setTargetFragment(this, 0)
            dialog.show(parentFragmentManager, "TimeSlotDialogFragment")
        }

        binding.btnConfirmOrders.setOnClickListener {
            val address = binding.edtCheckOutAddress.text.toString().trim()
            val deliveryTime = binding.tvCheckOutTimeSlot.text.toString().trim()

            if (address.isNotEmpty() && deliveryTime.isNotEmpty()) {
                placeOrder(address, deliveryTime)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onTimeSlotSelected(timeSlot: String) {
        binding.tvCheckOutTimeSlot.text = timeSlot
    }

    private fun loadCartItems() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            db.collection("carts").document(userId)
                .collection("items")
                .get()
                .addOnSuccessListener { result ->
                    cartItemList.clear()
                    for (document in result) {
                        val cartItem = document.toObject(CartItem::class.java)
                        cartItem.id = document.id
                        cartItemList.add(cartItem)
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to load cart items", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun placeOrder(address: String, deliveryTime: String) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val orderId = UUID.randomUUID().toString()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val orderDate = dateFormat.format(Date())
            val order = Order(
                id = orderId,
                userId = userId,
                address = address,
                deliveryTime = deliveryTime,
                items = cartItemList,
                status = "Pending",
                orderDate = orderDate // Set the current date
            )

            db.collection("orders").document(orderId)
                .set(order)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Order placed successfully", Toast.LENGTH_SHORT).show()
                    clearCart(userId)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to place order", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun clearCart(userId: String) {
        db.collection("carts").document(userId)
            .collection("items")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    document.reference.delete()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(requireContext(), "Failed to clear cart", Toast.LENGTH_SHORT).show()
            }
    }
}
