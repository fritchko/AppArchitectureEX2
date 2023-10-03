package com.example.networktest1.network

import com.example.networktest1.model.FactDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {

    @GET("facts")
    suspend fun getFacts(@Query("page") page: Int): Response<FactDTO>

}