package com.register.coffeapp.data.repository

import android.util.Log
import com.register.coffeapp.data.database.AuthDataDao
import com.register.coffeapp.data.mapper.CoffeeMapper
import com.register.coffeapp.data.network.ApiService
import com.register.coffeapp.domain.entities.AuthRequest
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.entities.UserData
import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
): CoffeeRepository {

    override suspend fun register(authRequest: AuthRequest): UserData {
        return apiService.getRegInfo(authRequest)
    }

    override suspend fun signIn(authRequest: AuthRequest): UserData {
        return apiService.getSignInfo(authRequest)
    }

    override suspend fun getCafeInfo(token: String): List<Cafe> {
        return apiService.getCafeInfo(token)
    }

    override suspend fun getMenuItems(token: String, id: String): List<MenuItem> {
        val menu = apiService.getMenu(token, id)
        Log.d("MenuTag", "$menu")
        return menu
    }

}