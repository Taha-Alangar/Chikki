package com.tahaalangar.thee_mart.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.thee_mart.DetailScreen
import com.tahaalangar.thee_mart.Products
import com.tahaalangar.thee_mart.R

class HomeProductListAdapter(private val productList:Products,val context: Context): RecyclerView.Adapter<HomeProductListAdapter.HomeProductViewHolder>() {
    class HomeProductViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.home_product_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.home_product_card,parent,false)
        return HomeProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.products.size
    }

    override fun onBindViewHolder(holder: HomeProductViewHolder, position: Int) {

        val currentItem=productList.products[position]
        holder.itemView.findViewById<TextView>(R.id.home_product_title).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.home_product_category).text=currentItem.category
        holder.itemView.findViewById<TextView>(R.id.home_product_price).text="$ ${currentItem.price}"
        holder.itemView.findViewById<TextView>(R.id.home_product_discount).text="${currentItem.discountPercentage}% OFF"
        Picasso.get().load(currentItem.thumbnail).into(holder.image);

        holder.itemView.setOnClickListener {
            val intent=Intent(context, DetailScreen::class.java)
            intent.putExtra("title",currentItem.title)
            intent.putExtra("brand",currentItem.brand)
            intent.putExtra("discount",currentItem.discountPercentage)
            intent.putExtra("price",currentItem.price)
            intent.putExtra("stocks",currentItem.stock)
            intent.putExtra("thumbnail",currentItem.thumbnail)
            context.startActivity(intent)
        }


    }
}