package com.register.coffeapp.domain.repository

import com.register.coffeapp.domain.entities.AuthRequest
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.entities.UserData

interface CoffeeRepository {

    suspend fun register(authRequest: AuthRequest): UserData

    suspend fun signIn(authRequest: AuthRequest): UserData

    suspend fun getCafeInfo(token: String): List<Cafe>

    suspend fun getMenuItems(token: String, id: String): List<MenuItem>

}