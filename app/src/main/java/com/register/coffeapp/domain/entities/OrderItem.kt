package com.register.coffeapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class OrderItem(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val price: String,
    val count: Int
) : Parcelable
