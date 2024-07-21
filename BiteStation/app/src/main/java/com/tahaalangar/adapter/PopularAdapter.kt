package com.tahaalangar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.bitestation.databinding.PopularitemviewBinding

class PopularAdapter (private val items:List<String>,private val price:List<String>,private val image:List<Int>):RecyclerView.Adapter<PopularAdapter.PopularViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularitemviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item=items[position]
        val price=price[position]
        val images=image[position]
        holder.bind(item,price,images)
    }

    class PopularViewHolder (private val binding:PopularitemviewBinding):RecyclerView.ViewHolder(binding.root){
        private val imageView=binding.imagePopular
        fun bind(item: String,price:String, images: Int) {
            binding.foodNamePopular.text=item
            binding.pricePopular.text=price
            imageView.setImageResource(images)
        }


    }
}