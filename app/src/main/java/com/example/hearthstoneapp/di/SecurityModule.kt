package com.example.hearthstoneapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val securityModule = module {

    fun provideGson() = GsonBuilder().create()

    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient.Builder().build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://omgvamp-hearthstone-v1.p.rapidapi.com/")
        .addConverterFactory(GsonConverterFactory.create(factory))
        .client(client)
        .build()

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}
