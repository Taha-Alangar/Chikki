package com.tahaalangar.myapplicationsajkdjskd.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.tahaalangar.myapplicationsajkdjskd.MemoProjectItem
import com.tahaalangar.myapplicationsajkdjskd.R

class ChildDetailAdapter(private val context: Context, private val memoProjectList: List<MemoProjectItem>) : RecyclerView.Adapter<ChildDetailAdapter.ViewHolder>()   {
    class ViewHolder (itemView: View) :RecyclerView.ViewHolder(itemView){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_child_detail, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Return 1 to display the no data view if memoProjectList is empty
        return if (memoProjectList.isEmpty()) 1 else memoProjectList.size
    }

    override fun onBindViewHolder(holder: ViewHolder,  position: Int) {
        if (memoProjectList.isEmpty()){
            Picasso.get().load(R.drawable.no_data_found).into(holder.itemView.findViewById<ImageView>(R.id.childDetailImage))
        }
        else{
            Log.d("ChildDetailActivity", memoProjectList[position].images)
        holder.itemView.findViewById<TextView>(R.id.childDetailTitle).text = memoProjectList[position].title
        holder.itemView.findViewById<TextView>(R.id.childDetailDescription).text = memoProjectList[position].description
            Picasso.get().load(memoProjectList[position].images).into(holder.itemView.findViewById<ImageView>(R.id.childDetailImage))
    }
    }
}