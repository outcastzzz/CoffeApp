package com.register.coffeapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_data")
data class AuthDataDbModel(
    @PrimaryKey
    val login: String,
    val password: String
)