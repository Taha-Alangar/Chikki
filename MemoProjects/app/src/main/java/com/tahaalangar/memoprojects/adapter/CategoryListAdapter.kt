package com.tahaalangar.memoprojects.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.pojos.Categories

class CategoryListAdapter(val categoryList: List<Categories>): RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.category_image)
        val name = itemView.findViewById<TextView>(R.id.categoryTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_categories, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryList[position]
        holder.name.isSelected = true
        holder.image.setImageResource(category.image)
        holder.name.text = category.name
    }
}