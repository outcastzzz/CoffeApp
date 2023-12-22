package com.register.coffeapp.presentation

import okhttp3.Interceptor
import okhttp3.Response

class BearerTokenInterceptor(private val token: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}