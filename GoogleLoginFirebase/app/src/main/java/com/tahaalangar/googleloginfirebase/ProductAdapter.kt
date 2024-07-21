package com.tahaalangar.googleloginfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private val products: List<Products>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val type = itemView.findViewById<TextView>(R.id.productType)
        val company = itemView.findViewById<TextView>(R.id.productCompany)
        val price = itemView.findViewById<TextView>(R.id.productPrice)
        val stock = itemView.findViewById<TextView>(R.id.productStock)
        val liters = itemView.findViewById<TextView>(R.id.productLiters)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_products,parent,false)
        return ProductViewHolder(view)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.type.text=products[position].type
        holder.company.text=products[position].companyName
        holder.price.text=products[position].price.toString()
        holder.stock.text=products[position].stock
        holder.liters.text=products[position].liters.toString()
    }
}