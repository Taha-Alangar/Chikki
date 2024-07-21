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
import com.tahaalangar.memoprojects.fragments.ProjectDetailFragment
import com.tahaalangar.memoprojects.fragments.ProjectListFragment
import com.tahaalangar.memoprojects.pojos.MemoProjectItem

class ProjectListAdapter(private val context: Context, private val projectList: List<MemoProjectItem>): RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val projectListImages = itemView.findViewById<ImageView>(R.id.projectImages)
        val projectListTitle = itemView.findViewById<TextView>(R.id.projectTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_projects, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.projectListTitle.text = projectList[position].title
        holder.projectListTitle.isSelected = true
        Picasso.get().load(projectList[position].images).into(holder.projectListImages)
        holder.itemView.setOnClickListener {
            navigateToProjectDetailActivity(projectList[position].id.toInt(),projectList[position].title,projectList[position].title,projectList[position].images,projectList[position].description)
        }
    }
    private fun navigateToProjectDetailActivity(id: Int,name:String,title:String,image:String,description:String) {
        val fragment = ProjectDetailFragment()
        val bundle = Bundle()
        bundle.putInt("DETAIL_ID", id)
        bundle.putString("DETAIL_NAME", name)
        bundle.putString("DETAIL_TITLE", title)
        bundle.putString("DETAIL_IMAGE", image)
        bundle.putString("DETAIL_DESCRIPTION", description)
        fragment.arguments = bundle

        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()

    }

}