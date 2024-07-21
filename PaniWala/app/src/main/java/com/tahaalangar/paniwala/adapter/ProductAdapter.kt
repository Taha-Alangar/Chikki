package com.tahaalangar.paniwala.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.tahaalangar.paniwala.fragments.ProductDetailFragment
import com.tahaalangar.paniwala.pojo.Products

class ProductsAdapter(
    private val context: Context,
    private var products: List<Products>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val price: TextView = itemView.findViewById(R.id.price)
        val literOrMilliliter: TextView = itemView.findViewById(R.id.literOrMilliliter)
        val image:ImageView=itemView.findViewById(R.id.image)
        fun bind(product: Products) {
            name.text = product.name
            price.text = "${product.price} / Per bottle"
            Picasso.get().load(product.imageUrl).into(image)
            literOrMilliliter.text = when {
                product.liter != 0.0 && product.miliLiter == 0 -> "${product.liter} ltr"
                product.liter == 0.0 && product.miliLiter != 0 -> "${product.miliLiter} ml"
                else -> ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_allproduct, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("product_id", product.productId)
            }
            val productDetailFragment = ProductDetailFragment().apply {
                arguments = bundle
            }
            fragmentManager.beginTransaction()
                .replace(R.id.frame_container, productDetailFragment)
                .addToBackStack(null)
                .commit()
        }
    }
    // Filtered list to hold filtered products
    private var filteredProducts: List<Products> = products

    // Function to filter products based on search query
    fun filter(query: String) {
        filteredProducts = if (query.isEmpty()) {
            products
        } else {
            products.filter { it.name.contains(query, true) }
        }
        notifyDataSetChanged()
    }
}