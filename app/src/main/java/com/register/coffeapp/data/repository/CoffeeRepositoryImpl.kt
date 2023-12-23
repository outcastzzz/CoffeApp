package com.register.coffeapp.data.repository

import com.register.coffeapp.data.network.ApiService
import com.register.coffeapp.domain.entities.User
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.entities.UserData
import com.register.coffeapp.domain.repository.CoffeeRepository
import javax.inject.Inject

class CoffeeRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
): CoffeeRepository {

    override suspend fun register(user: User): UserData {
        val response = apiService.getRegInfo(user)
        return when(response.code()) {
            in 200..300 -> response.body()!!
            else -> UserData("", 0)
        }
    }

    override suspend fun signIn(user: User): UserData {
        val response = apiService.getSignInfo(user)
        return when(response.code()) {
            in 200..300 -> return response.body()!!
            else -> return UserData("", 0)
        }
    }

    override suspend fun getCafeInfo(token: String): List<Cafe> {
        val response = apiService.getCafeInfo(token)
        return when(response.code()) {
            in 200..300 -> return response.body()!!
            else -> return emptyList()
        }
    }

    override suspend fun getMenuItems(token: String, id: String): List<MenuItem> {
        val response = apiService.getMenu(token, id)
        return when(response.code()) {
            in 200..300 -> return response.body()!!
            else -> return emptyList()
        }
    }

}