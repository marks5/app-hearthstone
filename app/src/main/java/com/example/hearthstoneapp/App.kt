package com.example.hearthstoneapp

import android.app.Application
import com.example.hearthstoneapp.di.applicationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(applicationModules)
        }
    }
}