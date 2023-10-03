package com.example.networktest1

import android.app.Application
import com.example.networktest1.network.FactProvider
import okhttp3.logging.HttpLoggingInterceptor

class FactApplication: Application() {

    private val loggingInterceptor = HttpLoggingInterceptor()

    val factory = MainViewModelFactory(FactProvider(loggingInterceptor))

    override fun onCreate() {
        super.onCreate()

        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    }

}