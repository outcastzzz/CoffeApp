package com.register.coffeapp.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.register.coffeapp.databinding.OrderItemBinding
import com.register.coffeapp.domain.entities.OrderItem

class OrderAdapter: ListAdapter<OrderItem, OrderViewHolder>(OrderItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = getItem(position)
        Log.d("OrderTag", "$order")
        holder.binding.tvName.text = order.name
        holder.binding.tvCount.text = order.count.toString()
        holder.binding.tvPrice.text = order.price
    }

}