package com.tahaalangar.blooddoners

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BloodDonnerAdapter(val dataList:BloodDoners ,var context: Context): RecyclerView.Adapter<BloodDonnerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view= LayoutInflater.from(parent.context).inflate(R.layout.blood_donner_item_card,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.states.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem=dataList.states[position]
        holder.itemView.findViewById<TextView>(R.id.id_tv).text="Id:${currentItem.id}"
        holder.itemView.findViewById<TextView>(R.id.name_tv).text="stateName:${currentItem.name}"
        holder.itemView.findViewById<TextView>(R.id.country_id_tv).text="CountryId:${currentItem.country_id}"


    }
}