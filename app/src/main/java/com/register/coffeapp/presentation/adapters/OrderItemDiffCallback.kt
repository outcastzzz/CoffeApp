package com.register.coffeapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.OrderItem

object OrderItemDiffCallback: DiffUtil.ItemCallback<OrderItem>() {

    override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem.name.hashCode() == newItem.name.hashCode()
    }

    override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem): Boolean {
        return oldItem == newItem
    }
}