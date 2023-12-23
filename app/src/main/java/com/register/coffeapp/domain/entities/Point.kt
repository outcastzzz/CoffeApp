package com.register.coffeapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class Point (
    val latitude: BigDecimal,
    val longitude: BigDecimal
): Parcelable