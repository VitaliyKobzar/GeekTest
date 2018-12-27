package com.example.geek.geektest.gateway

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Gateway {
    private const val baseUrl = "https://api.weatherbit.io/v2.0/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor {chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }
        .build()
    fun <T> create(api: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(baseUrl)
            .client(client)
            .build()

        return retrofit.create(api)
    }
}