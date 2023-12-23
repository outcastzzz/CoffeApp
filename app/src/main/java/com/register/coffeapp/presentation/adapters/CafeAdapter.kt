package com.register.coffeapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.register.coffeapp.databinding.CafeItemBinding
import com.register.coffeapp.domain.entities.Cafe
import kotlin.random.Random

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
        val distance = Random.nextInt(1, 3)
        holder.binding.tvDistance.text = "$distance км от вас"
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