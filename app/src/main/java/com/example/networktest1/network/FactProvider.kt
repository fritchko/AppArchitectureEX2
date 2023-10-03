package com.example.networktest1.network

import com.example.networktest1.model.Fact
import com.example.networktest1.model.toDomain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FactProvider(val loggingInterceptor: HttpLoggingInterceptor) {

    val baseUrl = "https://catfact.ninja/"

    val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(httpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

    val service = retrofit.create(FactService::class.java)

    suspend fun getFacts(page: Int): Fact {
        return service.getFacts(page).toDomain()
    }

}