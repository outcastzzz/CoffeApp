package com.register.coffeapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderItem(
    val name: String,
    val price: String,
    val count: Int
) : Parcelable
