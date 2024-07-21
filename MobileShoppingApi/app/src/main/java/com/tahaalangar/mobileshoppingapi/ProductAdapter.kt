package com.tahaalangar.mobileshoppingapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ProductAdapter (val dataList:Products ): RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image=itemView.findViewById<ImageView>(R.id.thumbnailImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.products_items_card,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem=dataList.products[position]
        holder.itemView.findViewById<TextView>(R.id.title).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.rating).text=currentItem.rating.toString()
        holder.itemView.findViewById<TextView>(R.id.price).text=currentItem.price.toString()
        holder.itemView.findViewById<TextView>(R.id.discount).text=currentItem.discountPercentage.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.image);




    }
}