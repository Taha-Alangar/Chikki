package com.tahaalangar.onlineshoppingapp.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.fragments.DetailFragment
import com.tahaalangar.onlineshoppingapp.pojos.AllProduct
import com.tahaalangar.onlineshoppingapp.pojos.Product

class BestSeller_Adapter(private val productList:AllProduct,val context: Context): RecyclerView.Adapter<BestSeller_Adapter.BestSellerProductViewHolder>()  {

    class BestSellerProductViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.bestSeller_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerProductViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bestseller,parent,false)
        return BestSellerProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.products.size
    }

    override fun onBindViewHolder(holder: BestSellerProductViewHolder, position: Int) {
        val currentItem=productList.products[position]
        holder.itemView.findViewById<TextView>(R.id.whishList_title_TV).text=currentItem.title
        holder.itemView.findViewById<TextView>(R.id.whishList_price_TV).text="$ ${currentItem.price}"
        holder.itemView.findViewById<TextView>(R.id.whishList_raing_Tv).text=currentItem.rating.toString()
        Picasso.get().load(currentItem.thumbnail).into(holder.image)

        holder.itemView.setOnClickListener {
            navigateToDetailFragment(currentItem)
        }

    }

    private fun navigateToDetailFragment(product: Product) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putSerializable("product", product)
        fragment.arguments = bundle

        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}