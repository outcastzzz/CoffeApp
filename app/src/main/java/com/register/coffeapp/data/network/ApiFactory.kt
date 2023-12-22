package com.register.coffeapp.data.network

import com.google.gson.Gson
import com.register.coffeapp.domain.entities.UserData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "http://147.78.66.203:3210/"

    private fun setupInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private val client = setupInterceptor()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val apiService = retrofit.create(ApiService::class.java)

}