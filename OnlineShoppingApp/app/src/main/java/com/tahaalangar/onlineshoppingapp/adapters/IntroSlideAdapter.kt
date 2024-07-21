package com.tahaalangar.onlineshoppingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.pojos.IntroSlide

class IntroSlideAdapter (private val introSlide: List<IntroSlide>): RecyclerView.Adapter<IntroSlideAdapter.IntroViewHolder>() {
    class IntroViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        private val textTitle=itemView.findViewById<TextView>(R.id.intorSlide_title_Tv)
        private val textDescription=itemView.findViewById<TextView>(R.id.introSlide_desc_Tv)
        private val imageIcon=itemView.findViewById<ImageView>(R.id.intoSlide_image)

        fun bind(introSlide: IntroSlide){
            textTitle.text=introSlide.title
            textDescription.text=introSlide.description
            imageIcon.setImageResource(introSlide.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container,parent,false)
        return IntroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return introSlide.size
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(introSlide[position])
    }
}