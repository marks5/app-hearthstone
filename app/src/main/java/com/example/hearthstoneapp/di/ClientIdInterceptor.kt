package com.example.hearthstoneapp.di

import okhttp3.Interceptor
import okhttp3.Response

class ClientIdInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .addHeader("X-RapidAPI-Key", "88943bb5e0mshe4b3937c0f6f3a8p1b9321jsn5f5ae4c330ea")
            .build()

        return chain.proceed(newRequest)
    }
}