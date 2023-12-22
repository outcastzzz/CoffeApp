package com.register.coffeapp.data.network

import com.register.coffeapp.domain.entities.AuthRequest
import com.register.coffeapp.domain.entities.Cafe
import com.register.coffeapp.domain.entities.MenuItem
import com.register.coffeapp.domain.entities.UserData
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth/register")
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getRegInfo(
        @Body authRequest: AuthRequest
    ): UserData

    @POST("auth/login")
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getSignInfo(
        @Body authRequest: AuthRequest
    ): UserData

    @GET("location/{id}/menu")
    suspend fun getMenu(
        @Header("Authorization") auth: String,
        @Path("id") id: String,
    ): List<MenuItem>

    @GET("locations")
    suspend fun getCafeInfo(
        @Header("Authorization") auth: String
    ): List<Cafe>

}