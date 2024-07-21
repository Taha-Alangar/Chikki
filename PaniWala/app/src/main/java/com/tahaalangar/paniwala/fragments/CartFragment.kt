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
import com.tahaalangar.paniwala.adapter.CartAdapter
import com.tahaalangar.paniwala.databinding.FragmentCartBinding
import com.tahaalangar.paniwala.pojo.CartItem


class CartFragment : Fragment() {
private lateinit var binding: FragmentCartBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var cartAdapter: CartAdapter
    private var cartItemList: MutableList<CartItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()


        setupRecyclerView()
        loadCartItems()
        binding.btnCartCheckOuts.setOnClickListener {
            val checkoutFragment = CheckOutFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_container, checkoutFragment)
                .addToBackStack(null) // This line adds the transaction to the back stack
                .commit()
        }
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(requireContext(), cartItemList, parentFragmentManager)
        binding.RVCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
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
                    cartAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), "Failed to load cart items: ${exception.message}", Toast.LENGTH_SHORT).show()
                    // You can add retry logic here if needed
                }
        }
    }

}