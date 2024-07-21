package com.tahaalangar.pharmacy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MedicineAdapter(private val context: Context,val adapterListerner: AdapterListerner): RecyclerView.Adapter<MedicineAdapter.RDViewHolder>()  {

    val arrayList:ArrayList<MedicinePojo> =ArrayList<MedicinePojo>()

    class RDViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        val editImage:ImageView=itemView.findViewById(R.id.editImage)
        val deleteImage:ImageView=itemView.findViewById(R.id.deleteImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RDViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.medicine_card,parent,false)
        return RDViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: RDViewHolder, position: Int) {
        val currentItem = arrayList[position]
        holder.itemView.findViewById<TextView>(R.id.medicineName).text=currentItem.medicineNames
        holder.itemView.findViewById<TextView>(R.id.medicineManufacturer).text=currentItem.manufacturer
        holder.itemView.findViewById<TextView>(R.id.quantity).text=currentItem.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.expiryDate).text=currentItem.expiryDate.toString()

        holder.editImage.setOnClickListener {
            adapterListerner.onUpdate(arrayList[position])
        }
        holder.deleteImage.setOnClickListener {
            adapterListerner.onDelete(arrayList[position])
        }
    }

}