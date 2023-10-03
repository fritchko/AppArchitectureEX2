package com.example.networktest1.network

import com.example.networktest1.model.Fact
import com.example.networktest1.model.toDomain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FactsRepo {

    private var factsService: FactService? = null

    suspend fun getFacts(page: Int): Fact? {
        if(factsService == null){
            factsService = createRetrofitInstance().create(FactService::class.java)
        }

        val response = factsService?.getFacts(page)

        return response?.toDomain()

    }

    fun createRetrofitInstance(): Retrofit{
        val baseUrl = "https://catfact.ninja/"

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}