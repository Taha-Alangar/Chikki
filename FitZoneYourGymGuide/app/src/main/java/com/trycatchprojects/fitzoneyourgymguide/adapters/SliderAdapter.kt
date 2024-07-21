package com.trycatchprojects.fitzoneyourgymguide.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.trycatchprojects.fitzoneyourgymguide.R
import com.trycatchprojects.fitzoneyourgymguide.models.SlideItem

class SliderAdapter(private val items: List<SlideItem>) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleView: TextView = view.findViewById(R.id.titleView)
        val descriptionView: TextView = view.findViewById(R.id.descriptionView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.titleView.text = item.title
        holder.descriptionView.text = item.description
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
