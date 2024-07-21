package com.tahaalangar.myapplicationsajkdjskd.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.myapplicationsajkdjskd.ChildCategoryItem
import com.tahaalangar.myapplicationsajkdjskd.R
import com.tahaalangar.myapplicationsajkdjskd.screens.ChildDetailActivity

class ChildAdapter(private val context: Context, private val childList: List<ChildCategoryItem>) : RecyclerView.Adapter<ChildAdapter.ViewHolder>()  {
    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_parent, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.parentName).text = childList[position].cat_name
        Picasso.get().load(childList[position].cat_img).into(holder.itemView.findViewById<ImageView>(R.id.imageView))
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChildDetailActivity::class.java)
            intent.putExtra("CATEGORY_ID", childList[position].id.toInt())
            context.startActivity(intent)
        }
    }
}