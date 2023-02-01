package com.example.hearthstoneapp.di

import com.example.hearthstoneapp.data.CharService
import org.koin.dsl.module


val networkServiceModule = module {
    single {
        ApiClientBuilder.createService(
            CharService::class.java,
            "https://omgvamp-hearthstone-v1.p.rapidapi.com/",
            interceptors = arrayOf(ClientIdInterceptor())
        )
    }
}
