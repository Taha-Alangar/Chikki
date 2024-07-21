package com.tahaalangar.thee_mart.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.thee_mart.R
import com.tahaalangar.thee_mart.CategoryLists
import com.tahaalangar.thee_mart.ProductDetail

class CategoryListAdapter(val productDetails:CategoryLists,val context: Context):RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>(){

    class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.category_list_card,parent,false)
        return CategoryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return productDetails.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        val currentItem=productDetails[position]
        holder.itemView.findViewById<TextView>(R.id.category_tv).text=currentItem

        holder.itemView.setOnClickListener {
            val intent=Intent(context,ProductDetail::class.java)
            intent.putExtra("category",currentItem)
            context.startActivity(intent)
        }
    }

}