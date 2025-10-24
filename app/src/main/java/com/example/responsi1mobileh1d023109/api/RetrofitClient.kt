package com.example.responsi1mobileh1d023109.api
// Lokasi: com.yournim.responsi1mobile.api/RetrofitClient.kt

import com.example.responsi1mobileh1d023073.api.FootballApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://api.football-data.org/"

    const val API_TOKEN = "000114fc01aa4970bb091523659dc1d6"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {

        level = HttpLoggingInterceptor.Level.BODY
    }

    // Konfigurasi Klien HTTP (OkHttp)
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)

        .build()

    // Inisialisasi Retrofit
    val apiService: FootballApiService by lazy {
        Builder()
            .baseUrl(BASE_URL)
            // Menentukan klien HTTP yang digunakan
            .client(okHttpClient)
            // Menentukan konverter (Gson) untuk JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            // Membuat implementasi dari FootballApiService
            .create(FootballApiService::class.java)
    }
}