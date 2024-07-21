package com.tahaalangar.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RDAdapter(private val context: Context, private var adapterListener: AdpaterListener): RecyclerView.Adapter<RDAdapter.RDViewHolder>() {
    // why we don't pass the contact in the parameter of the adapter because a beginning we don't have data so app may crash

    private var arrayList:ArrayList<Contact> = ArrayList<Contact>()

    fun addUser(pojo:Contact){
        arrayList.add(pojo)
    }
    fun clearData(){
        arrayList.clear()
    }
    fun addAll(users: List<Contact>) {
        arrayList.addAll(users)
    }

    class RDViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        val editImage: ImageView = itemView.findViewById(R.id.editImageCard)
        val deleteImage: ImageView = itemView.findViewById(R.id.deleteimageCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RDViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_card_itemview,parent,false)
        return RDViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RDViewHolder, position: Int) {
        val currentItem = arrayList[position]
        holder.itemView.findViewById<TextView>(R.id.searchFruitsNameCard).text = "Name: ${currentItem.name}"
        holder.itemView.findViewById<TextView>(R.id.searchFruitsWeightCard).text = "Weight: ${currentItem.weight} Kg"
        holder.itemView.findViewById<TextView>(R.id.searchFruitsPriceCard).text = "Price: ${currentItem.price} $"

        holder.editImage.setOnClickListener {   // this i how i can navigate to update screen while clicking on edit image
            adapterListener.onUpdate(arrayList[position])
        }
        holder.deleteImage.setOnClickListener {
            adapterListener.onDelete(arrayList[position])
        }
    }
}