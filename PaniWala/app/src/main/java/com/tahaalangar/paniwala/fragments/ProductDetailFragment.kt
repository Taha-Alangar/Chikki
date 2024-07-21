package com.tahaalangar.paniwala.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.databinding.FragmentProductDetailBinding
import com.tahaalangar.paniwala.pojo.Products


class ProductDetailFragment : Fragment() {
  private lateinit var binding: FragmentProductDetailBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private var productId: String? = null
    private lateinit var product: Products
    private var quantity: Int = 1  // Variable to keep track of the quantity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        productId = arguments?.getString("product_id")
        if (productId != null) {
            loadProductDetails(productId!!)
        }

        binding.PDAddToCart.setOnClickListener {
            addToCart()
        }
        binding.increaseProductQuantity.setOnClickListener {
            increaseQuantity()
        }
        binding.decreaseProductQuantity.setOnClickListener {
            decreaseQuantity()
        }

    }
    private fun updateQuantityDisplay() {
        binding.tvPDQauntity.text = quantity.toString()
    }

    private fun increaseQuantity() {
        quantity++
        updateQuantityDisplay()
    }

    private fun decreaseQuantity() {
        if (quantity > 1) {
            quantity--
            updateQuantityDisplay()
        }
    }

    private fun loadProductDetails(productId: String) {
        db.collection("products").document(productId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    product = document.toObject(Products::class.java)!!
                    product.productId = document.id
                    Picasso.get().load(product.imageUrl).into(binding.imgPDImage)
                    binding.tvPDName.text = product.name
                    binding.tvPDCategory.text = product.category
                    binding.tvPDPrice.text = "${product.price} / Per bottle"
                    updateQuantityDisplay()
                } else {
                    Toast.makeText(requireContext(), "Product not found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to load product details", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addToCart() {
        val userId = auth.currentUser?.uid ?: return
        val cartItem = hashMapOf(
            "productId" to product.productId,
            "productName" to product.name,
            "quantity" to quantity,
            "price" to product.price,
            "imageUrl" to product.imageUrl // Add the image URL to cart item
        )

        db.collection("carts").document(userId)
            .collection("items").add(cartItem)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show()
            }
    }
}