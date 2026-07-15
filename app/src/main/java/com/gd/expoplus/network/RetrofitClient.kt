package com.gd.expoplus.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL =
        "https://lldvcovulcblbqoxpznh.supabase.co/functions/v1/"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()

        .connectTimeout(30, TimeUnit.SECONDS)

        .readTimeout(60, TimeUnit.SECONDS)

        .writeTimeout(60, TimeUnit.SECONDS)

        .addInterceptor(logger)

        .build()

    val assistantApi: AssistantApi by lazy {

        Retrofit.Builder()

            .baseUrl(BASE_URL)

            .client(client)

            .addConverterFactory(
                GsonConverterFactory.create()
            )

            .build()

            .create(AssistantApi::class.java)
    }
}