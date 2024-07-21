package com.tahaalangar.paniwalaadmin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.tahaalangar.paniwalaadmin.AllProductActivity
import com.tahaalangar.paniwalaadmin.R
import com.tahaalangar.paniwalaadmin.pojos.Products

class AllProductsAdapter(private val products: MutableList<Products> ,private val onItemClicked: (Products) -> Unit): RecyclerView.Adapter<AllProductsAdapter.ViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    class ViewHolder (itmeView: View, private val onItemClicked: (Products) -> Unit):RecyclerView.ViewHolder(itmeView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val category: TextView = itemView.findViewById(R.id.category)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val stock: TextView = itemView.findViewById(R.id.stock)
        private val literOrMilliLiter: TextView = itemView.findViewById(R.id.LiterOrMilliliter)
        private val productImage: ImageView = itemView.findViewById(R.id.image)
        private val increaseStock: ImageView = itemView.findViewById(R.id.increaseStock)
        private val decreaseStock: ImageView = itemView.findViewById(R.id.decreaseStock)
        private val deleteStock: ImageView = itemView.findViewById(R.id.deleteStock)


        fun bind(product: Products, db: FirebaseFirestore) {
            name.text = product.name
            category.text = product.category
            price.text = product.price.toString()
            stock.text = product.stocks.toString()
            literOrMilliLiter.text = when {
                product.liter != 0.0 && product.milliLiter == 0 -> product.liter.toString()
                product.milliLiter != 0 && product.liter == 0.0 -> product.milliLiter.toString()
                else -> ""
            }

            Picasso.get().load(product.imageUrl).into(productImage)

            itemView.setOnClickListener {
                onItemClicked(product)
            }
            increaseStock.setOnClickListener {
                updateStock(product, db, 1)
            }

            decreaseStock.setOnClickListener {
                updateStock(product, db, -1)
            }

            deleteStock.setOnClickListener {
                deleteProduct(product, db)
            }
        }


        private fun updateStock(product: Products, db: FirebaseFirestore, change: Int) {
            val newStock = product.stocks + change
            if (newStock >= 0) {
                db.collection("products").document(product.productId)
                    .update("stocks", newStock)
                    .addOnSuccessListener {
                        stock.text = newStock.toString()
                        product.stocks = newStock
                        Toast.makeText(itemView.context, "Stock updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            itemView.context,
                            "Error updating stock: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(itemView.context, "Stock cannot be less than 0", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        private fun deleteProduct(product: Products, db: FirebaseFirestore) {
            db.collection("products").document(product.productId)
                .delete()
                .addOnSuccessListener {
                    /// Notify adapter to remove the product
                    (itemView.context as? AllProductActivity)?.removeProduct(adapterPosition)
                    Toast.makeText(itemView.context, "Product deleted", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(itemView.context, "Error deleting product: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    // Function to update dataset when an item is deleted
    fun removeItem(position: Int) {
        products.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, products.size)
    }
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.viewholder_allproducts,parent,false)
        return ViewHolder(view,onItemClicked)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position], db)

    }
}
