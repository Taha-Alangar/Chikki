package com.tahaalangar.onlineshoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.pojos.CategoryList
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListImages

class CategoryList_Apater(val productDetails: CategoryList,
                          val productLogo:List<CategoryListImages>,
                          val context: Context,private val onItemClicked: (String) -> Unit ):
    RecyclerView.Adapter<CategoryList_Apater.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val productLogo:ImageView=itemView.findViewById(R.id.category_image)
        val categoryTittle:TextView=itemView.findViewById(R.id.category_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category,parent,false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productDetails.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentTitleItem=productDetails[position]
        holder.categoryTittle.isSelected=true
        holder.itemView.findViewById<TextView>(R.id.category_title).text=currentTitleItem
        Picasso.get().load(productLogo[position].categoryImage).into(holder.productLogo);
        // Set the click listener for the item
        holder.itemView.setOnClickListener {
            onItemClicked(currentTitleItem)
        }
    }
}