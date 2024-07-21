package com.tahaalangar.paniwala.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.tahaalangar.paniwala.R
import com.tahaalangar.paniwala.fragments.OrdeDetailFragment
import com.tahaalangar.paniwala.fragments.ProductDetailFragment
import com.tahaalangar.paniwala.pojo.Order

class OrdersAdapter(
    private val context: Context,
    private val orders: List<Order>,
   private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
        holder.itemView.setOnClickListener {
            val orderId = order.id
            val bundle = Bundle().apply {
                putString("orderId", orderId)
            }
            val fragment = OrdeDetailFragment()
            fragment.arguments = bundle
            Log.d("OrdersAdapter", "Attempting to replace fragment with orderId: $orderId")
            // Navigate to OrderDetailFragment
            try {
                fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .addToBackStack(null)
                    .commit()
            } catch (e: Exception) {
                Log.e("OrdersAdapter", "Error replacing fragment", e)
            }
        }
    }

    override fun getItemCount() = orders.size

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val address = itemView.findViewById<TextView>(R.id.tvOrderAddress)
        val deliveryTime = itemView.findViewById<TextView>(R.id.tvOrderDeliveryTime)
        val status = itemView.findViewById<TextView>(R.id.tvOrderStatus)
        val total = itemView.findViewById<TextView>(R.id.tvOrderTotal)

        fun bind(order: Order) {
            address.text = "${order.address}"
            deliveryTime.text = "${order.deliveryTime}"
            status.text = "${order.status}"
            if (order.status=="Delivered") {
                status.setTextColor(context.getColor(R.color.green))
            } else if (order.status=="Pending"){
                status.setTextColor(context.getColor(R.color.red))
            }
            total.text = "You pay ${order.items.sumOf { it.price * it.quantity }} Rs"

        }
    }
}