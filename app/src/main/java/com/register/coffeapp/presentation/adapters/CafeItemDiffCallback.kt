package com.register.coffeapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.register.coffeapp.domain.entities.Cafe

object CafeItemDiffCallback: DiffUtil.ItemCallback<Cafe>() {

    override fun areItemsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
        return oldItem == newItem
    }
}