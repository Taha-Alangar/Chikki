package com.tahaalangar.paniwalaadmin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tahaalangar.paniwalaadmin.R
import com.tahaalangar.paniwalaadmin.pojos.CartItem

class OrderProductAdapter(
    private val context: Context,
    private val products: List<CartItem>
) : RecyclerView.Adapter<OrderProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_order_products, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount() = products.size

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productName: TextView = itemView.findViewById(R.id.tvProductName)
        private val productQuantity: TextView = itemView.findViewById(R.id.tvProductQuantity)
        private val productPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        private val literOrMilliLiter: TextView = itemView.findViewById(R.id.tvLiterOrMilliLiter)
        private val image: ImageView= itemView.findViewById(R.id.imgProductImage)




        fun bind(product: CartItem) {
            productName.text = product.productName
            productQuantity.text = "Quantity: ${product.quantity}"
            productPrice.text = "${product.price} / per bottle "
            if (!product.image.isNullOrEmpty()) {
                Picasso.get().load(product.image).into(image)
            } else {
                image.setImageResource(R.drawable.pani_wala_splash) // A placeholder image in case the URL is invalid
            }
            if (product.liter!=0.0 && product.milliLiter == 0){
                literOrMilliLiter.text = "${product.liter} ltr"
            }else if (product.liter == 0.0 && product.milliLiter != 0){
                literOrMilliLiter.text = "${product.milliLiter} ml"
            }
        }
    }
}