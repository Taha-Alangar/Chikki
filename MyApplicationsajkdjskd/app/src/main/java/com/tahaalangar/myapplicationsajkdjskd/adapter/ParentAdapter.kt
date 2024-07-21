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
import com.tahaalangar.myapplicationsajkdjskd.ParentCategoryItem
import com.tahaalangar.myapplicationsajkdjskd.R
import com.tahaalangar.myapplicationsajkdjskd.screens.ChildActivity

class ParentAdapter(private val context: Context, private val parentList: List<ParentCategoryItem>) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_parent, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return parentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.parentName).text = parentList[position].cat_name
        Picasso.get().load(parentList[position].cat_image).into(holder.itemView.findViewById<ImageView>(R.id.imageView))
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChildActivity::class.java)
            intent.putExtra("PARENT_ID", parentList[position].id.toInt())
            context.startActivity(intent)
        }


    }
}