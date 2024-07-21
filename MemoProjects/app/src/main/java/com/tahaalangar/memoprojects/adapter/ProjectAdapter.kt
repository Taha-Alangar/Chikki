package com.tahaalangar.memoprojects.adapter

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
import com.tahaalangar.memoprojects.R
import com.tahaalangar.memoprojects.fragments.ProjectCategoryListFragment
import com.tahaalangar.memoprojects.pojos.ParentCategoryItem

class ProjectAdapter(private val context: Context, private val projectList: List<ParentCategoryItem>):
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>()  {
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val projectImages = itemView.findViewById<ImageView>(R.id.projectImages)
        val projectTitle = itemView.findViewById<TextView>(R.id.projectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_projects, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.projectTitle.text = projectList[position].cat_name
        holder.projectTitle.isSelected = true
        Picasso.get().load(projectList[position].cat_image).into(holder.projectImages)
        holder.itemView.setOnClickListener {
            navigateToProjectListFragment(projectList[position].id.toInt(),projectList[position].cat_name)
        }
    }
    private fun navigateToProjectListFragment(parentId: Int,name:String) {
        val fragment = ProjectCategoryListFragment()
        val bundle = Bundle()
        bundle.putInt("PARENT_ID", parentId)
        bundle.putString("PARENT_NAME", name)
        fragment.arguments = bundle

        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}