package com.register.coffeapp.domain.entities

data class UserData (
    val token: String,
    val tokenLifetime: Int
)