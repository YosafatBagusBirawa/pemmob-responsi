package com.example.responsi1mobileh1d023109.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.football-data.org/"

    const val API_TOKEN = "000114fc01aa4970bb091523659dc1d6"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {

        level = HttpLoggingInterceptor.Level.BODY
    }


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        .addInterceptor(loggingInterceptor)

        .build()

    val apiService: FootballApiService by lazy {
        Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApiService::class.java)
    }
}