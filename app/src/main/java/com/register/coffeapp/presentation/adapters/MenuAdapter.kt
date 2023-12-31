package com.register.coffeapp.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.register.coffeapp.R
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.entities.OrderItem

class MenuAdapter(
    private val context: Context,
    private val list: List<MenuItem>,
    private val listener: OnMenuItemCountChangedListener
): BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
            .from(parent?.context)
            .inflate(
                R.layout.menu_item,
                parent,
                false
            )
        val image = view.findViewById<ImageView>(R.id.coffee_image)
        val price = view.findViewById<TextView>(R.id.price_tv)
        val name = view.findViewById<TextView>(R.id.coffee_name_tv)
        val countOfCoffee = view.findViewById<TextView>(R.id.tv_count)
        val countIncrement = view.findViewById<TextView>(R.id.tv_increment)
        val countDecrement = view.findViewById<TextView>(R.id.tv_decrement)

        val imageStr = list[position].imageURL

        Glide.with(context)
            .load(imageStr)
            .centerCrop()
            .into(image)
        val priceName = list[position].price.toString()
        price.text = "$priceName руб"
        name.text = list[position].name

        var countItems = 0

        countIncrement.setOnClickListener {
            countItems += 1
            countOfCoffee.text = countItems.toString()
            val priceOfItems = list[position].price * countItems
            val orderItem = OrderItem(
                name = list[position].name,
                price = priceOfItems.toString(),
                count = countItems
            )
            listener.onCountChanged(orderItem)
        }

        countDecrement.setOnClickListener {
            if (count > 0) {
                countItems -= 1
                countOfCoffee.text = countItems.toString()
                val orderItem = OrderItem(
                    name = list[position].name,
                    price = list[position].price.toString(),
                    count = countItems
                )
                listener.onCountChanged(orderItem)
            } else {
                val countUpdated = --countItems
                countOfCoffee.text = countUpdated.toString()
            }
        }

        return view
    }

    interface OnMenuItemCountChangedListener {
        fun onCountChanged(orderItem: OrderItem)
    }

}