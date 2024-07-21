package com.tahaalangar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.bitestation.databinding.CartItemBinding

class CartAdapter(private val CartItems:MutableList<String>,private val CartPrice:MutableList<String>,private val CartImage:MutableList<Int>):RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
private val itemQuantites=IntArray(CartItems.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartViewHolder {
        val binding=CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }



    override fun onBindViewHolder(holder: CartAdapter.CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return CartItems.size
    }

    inner class CartViewHolder (private val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                val quantity=itemQuantites[position]
                cartFoodName.text=CartItems[position]
                cartitemPrice.text=CartPrice[position]
                cartImage.setImageResource(CartImage[position])
                cartItemQuantity.text=quantity.toString()

                minusButton.setOnClickListener{
                    decreasedQuantity(position)
                }
                plusButton.setOnClickListener {
                    increasedQuantity(position)
                }
                deleteButton.setOnClickListener {
                    val itemPosition=adapterPosition
                    if (itemPosition!=RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)
                    }
                }


            }

        }
        private fun deleteItem(position: Int){
            CartItems.removeAt(position)
            CartImage.removeAt(position)
            CartPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,CartItems.size)
        }
        private fun increasedQuantity(position: Int){
            if (itemQuantites[position]<10){
                itemQuantites[position]++
                binding.cartItemQuantity.text=itemQuantites[position].toString()
            }
        }
         private fun decreasedQuantity(position: Int) {
            if (itemQuantites[position] > 1) {
                itemQuantites[position]--
                binding.cartItemQuantity.text = itemQuantites[position].toString()
            }
        }
    }
}