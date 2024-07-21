package com.tahaalangar.onlineshoppingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.onlineshoppingapp.R
import com.tahaalangar.onlineshoppingapp.pojos.AllCarts
import com.tahaalangar.onlineshoppingapp.pojos.AllProduct
import com.tahaalangar.onlineshoppingapp.pojos.CartPojo

class CartAdapter(private val productList: CartPojo, val context: Context): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.cart_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart,parent,false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.products.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        val currentCart = productList.products[position]
        holder.itemView.findViewById<TextView>(R.id.cart_title).text = currentCart.title
        Picasso.get().load(currentCart.thumbnail).into(holder.image)
        holder.itemView.findViewById<TextView>(R.id.cart_quantity).text = currentCart.quantity.toString()
        holder.itemView.findViewById<TextView>(R.id.cart_total).text = "You Pay $${currentCart.price.toString()}"
        holder.itemView.findViewById<TextView>(R.id.cart_dicountTotal).text = " You Save ${currentCart.discountedTotal.toString().take(3)}"

    }
}