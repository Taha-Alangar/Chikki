package com.tahaalangar.memoprojects.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.pojos.AllCategory
import com.tahaalangar.memoprojects.pojos.MemoProjectData

class AllProjectsAdapter(val context: Context,private val projects: MemoProjectData,
): RecyclerView.Adapter<AllProjectsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image:ImageView=itemView.findViewById(R.id.projectImages)
        val title=itemView.findViewById<TextView>(R.id.projectTitle)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_projects, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projects.all_categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text=projects.all_categories[position].cat_name
        holder.title.isSelected=true
        Picasso.get().load(projects.all_categories[position].cat_image).into(holder.image)
    }
}