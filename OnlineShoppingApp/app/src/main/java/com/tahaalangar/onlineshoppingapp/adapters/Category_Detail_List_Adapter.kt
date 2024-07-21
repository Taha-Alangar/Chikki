package com.tahaalangar.onlineshoppingapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.fragments.DetailFragment
import com.tahaalangar.onlineshoppingapp.pojos.CategoryList
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListImages
import com.tahaalangar.onlineshoppingapp.pojos.CategoryListPojo
import com.tahaalangar.onlineshoppingapp.pojos.Product

class Category_Detail_List_Adapter (val categoryListPojo:CategoryListPojo, val context: Context): RecyclerView.Adapter<Category_Detail_List_Adapter.Category_List_ViewHolder>() {

    class Category_List_ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val image=itemView.findViewById<ImageView>(R.id.category_List_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Category_List_ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category_list,parent,false)
        return Category_List_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryListPojo.products.size
    }

    override fun onBindViewHolder(holder: Category_List_ViewHolder, position: Int) {

        val currentItem=categoryListPojo.products[position]
        holder.itemView.findViewById<TextView>(R.id.category_List_title_TV).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.category_List_price_TV).text="$ ${currentItem.price}"
        holder.itemView.findViewById<TextView>(R.id.category_List_raing_Tv).text=currentItem.rating.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.image)

        holder.itemView.setOnClickListener {
            navigateToDetailFragment(currentItem)
        }
    }
    private fun navigateToDetailFragment(product: Product) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putSerializable("product", product)
        fragment.arguments = bundle

        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack("SearchFragment")
            .commit()
    }
}