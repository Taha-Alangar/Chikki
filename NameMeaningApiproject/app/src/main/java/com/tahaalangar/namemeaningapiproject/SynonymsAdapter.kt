package com.tahaalangar.namemeaningapiproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SynonymsAdapter (val dataList:category_and_gender,var context: Context) : RecyclerView.Adapter<SynonymsAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SynonymsAdapter.MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.synonyms_card_itemview,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SynonymsAdapter.MyViewHolder, position: Int) {
        val currentItem=dataList[position]
        holder.itemView.findViewById<TextView>(R.id.nameTV).text=currentItem.name
        holder.itemView.findViewById<TextView>(R.id.meaningTv).text=currentItem.meaning
        holder.itemView.findViewById<TextView>(R.id.idTv).text=currentItem.id
        holder.itemView.findViewById<TextView>(R.id.categoryIdTv).text=currentItem.category_id
        holder.itemView.findViewById<TextView>(R.id.genderTv).text=currentItem.gender

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}