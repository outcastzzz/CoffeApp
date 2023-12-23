package com.register.coffeapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.OrderItem

object OrderItemDiffCallback: DiffUtil.ItemCallback<OrderItem>() {

    override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem == newItem
    }
}