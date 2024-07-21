package com.tahaalangar.myapplication22222

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val newsList: List<newItem>
,private val clickListener: (newItem) -> Unit): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title=itemView.findViewById<TextView>(R.id.newsTitle)
        val image=itemView.findViewById<ImageView>(R.id.newImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_news,parent,false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.text=newsList[position].news_title
        Log.d("NewsAdapter", "Image URL: ${newsList[position].news_image}")
        Picasso.get().load(newsList[position].news_image).into(holder.image)

        holder.itemView.setOnClickListener {
            clickListener(newsList[position])
        }
    }
}