package com.register.coffeapp.domain.entities

import java.math.BigDecimal

data class Cafe (
    val id: Int,
    val name: String,
    val point: Point
)

data class Point (
    val latitude: BigDecimal,
    val longitude: BigDecimal
)