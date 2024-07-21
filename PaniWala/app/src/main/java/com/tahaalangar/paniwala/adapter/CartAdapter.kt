package com.tahaalangar.paniwala.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.paniwala.ProductDetailActivity
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.adapter.ProductsAdapter.ViewHolder
import com.tahaalangar.paniwala.fragments.ProductDetailFragment
import com.tahaalangar.paniwala.pojo.CartItem

class CartAdapter(
    private val context: Context,
    private val cartItems: List<CartItem>,
    private val fragmentManager: FragmentManager

) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart,parent,false)
        return CartViewHolder(view)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.bind(cartItem)
//        holder.itemView.setOnClickListener {
//            val bundle = Bundle().apply {
//                putString("product_id", cartItem.productId)
//
//            }
//            val productDetailFragment = ProductDetailFragment().apply {
//                arguments = bundle
//            }
//            fragmentManager.beginTransaction()
//                .replace(R.id.frame_container, productDetailFragment)
//                .addToBackStack(null)
//                .commit()
//        }
    }

    override fun getItemCount() = cartItems.size

    inner class CartViewHolder(itmeView: View) : RecyclerView.ViewHolder(itmeView) {

        val name = itemView.findViewById<TextView>(R.id.tvCartItemName)
        val price = itemView.findViewById<TextView>(R.id.tvCartItemPrice)
        val quantity = itemView.findViewById<TextView>(R.id.tvCartItemQuantity)
        val quantityPerBottle = itemView.findViewById<TextView>(R.id.tvQuantityPerBottle)
        val totalPrice = itemView.findViewById<TextView>(R.id.tvCartItemTotalPrice)
        val images = itemView.findViewById<ImageView>(R.id.imgCartItemImage)
        val increase = itemView.findViewById<ImageView>(R.id.cartItemIncrease)
        val decrease = itemView.findViewById<ImageView>(R.id.cartItemDecrease)

        fun bind(cartItem: CartItem) {
             var totalQuantity: Int = cartItem.quantity // Variable to keep track of the quantity
            increase.setOnClickListener {
                totalQuantity++
                quantity.text = totalQuantity.toString()
            }
            decrease.setOnClickListener {
                if (totalQuantity > 1) {
                    totalQuantity--
                    quantity.text = totalQuantity.toString()
                }
            }


            val prices=cartItem.price*cartItem.quantity
            totalPrice.text="You pay: $prices"
            name.text = "Name: ${cartItem.productName}"
            price.text = "${cartItem.price} / Per bottle"
            quantity.text = "${cartItem.quantity}"
            quantityPerBottle.text = " ${cartItem.quantity} Per bottle"
            // Debug statement
            Log.d("CartAdapter", "Image URL: ${cartItem.image}")
            // Load image using Picasso
            if (cartItem.image.isNotEmpty()) {
                Picasso.get().load(cartItem.image).into(images)
            } else {
                // Provide a placeholder image or handle empty image URL case
                Picasso.get().load(R.drawable.bisleri).into(images)
            }
        }
    }
}