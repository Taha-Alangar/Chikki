package com.tahaalangar.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.recyclerview.databinding.RvItemviewCardBinding

class RvAdapter(var dataList:ArrayList<RvModel> , var context: Context):RecyclerView.Adapter<RvAdapter.MyViewHolder> (){


    inner class MyViewHolder(var binding:RvItemviewCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding=RvItemviewCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
         return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        anim(holder.itemView)
        holder.binding.profile.setImageResource(dataList[position].profile)
        holder.binding.name.text=dataList[position].name
        holder.binding.address.text=dataList[position].address
        holder.itemView.setOnClickListener {
            Toast.makeText(context, dataList[position].name, Toast.LENGTH_SHORT).show()
        }
    }
    fun anim(view: View){
        var animation=AlphaAnimation(0.0f,1.0f)
        animation.duration=2000
        view.startAnimation(animation)


    }
}