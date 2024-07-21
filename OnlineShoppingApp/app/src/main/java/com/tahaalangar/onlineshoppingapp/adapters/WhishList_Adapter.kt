package com.tahaalangar.onlineshoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.pojos.AllProduct

class WhishList_Adapter(private val productList: AllProduct, val context: Context): RecyclerView.Adapter<WhishList_Adapter.WhishListViewHolder>() {
    class WhishListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)  {
        val image=itemView.findViewById<ImageView>(R.id.whishList_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhishListViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_whishlist,parent,false)
        return WhishListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.products.size
    }

    override fun onBindViewHolder(holder: WhishListViewHolder, position: Int) {
        val currentItem=productList.products[position]
        holder.itemView.findViewById<TextView>(R.id.whishList_title_TV).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.whishList_price_TV).text="$ ${currentItem.price}"
        holder.itemView.findViewById<TextView>(R.id.whishList_raing_Tv).text="${currentItem.rating}"
        Picasso.get().load(currentItem.thumbnail).into(holder.image);


    }
}