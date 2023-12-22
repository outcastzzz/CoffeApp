package com.register.coffeapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.register.coffeapp.databinding.CafeItemBinding
import com.register.coffeapp.domain.entities.Cafe

class CafeAdapter: ListAdapter<Cafe, CafeViewHolder>(CafeItemDiffCallback) {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val binding = CafeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CafeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val cafe = getItem(position)
        holder.binding.tvName.text = cafe.name
        holder.binding.tvDistance.text = "1 км от вас"
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}