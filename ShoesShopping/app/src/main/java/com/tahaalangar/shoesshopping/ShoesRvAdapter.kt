package com.tahaalangar.shoesshopping

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ShoesRvAdapter (private var dataList:List<RvModel>, var context: Context):RecyclerView.Adapter<ShoesRvAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_shoes_itemvew, parent, false)
         return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
         return dataList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.itemView.setOnClickListener{
            val intent=Intent(context,CountryDetail::class.java)
            intent.putExtra("SHOE_IMAGE", currentItem.shoesImage)
            intent.putExtra("BRAND_NAME", currentItem.brandName)
            intent.putExtra("CATEGORY", currentItem.category)
            intent.putExtra("PRICE", currentItem.price)
            context.startActivity(intent)
        }

        holder.itemView.findViewById<ImageView>(R.id.shoes_image).setImageResource(currentItem.shoesImage)
        holder.itemView.findViewById<TextView>(R.id.brand_name).text=currentItem.brandName
        holder.itemView.findViewById<TextView>(R.id.category).text = currentItem.category
        holder.itemView.findViewById<TextView>(R.id.price).text = currentItem.price.toString()
    }
}