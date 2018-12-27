package com.example.geek.geektest.gateway

import com.example.geek.geektest.models.WeatherInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        val apiKey: String = "2f9ef2540c3141e5a007506b2a77a4b7"
    }

    @GET("current")
    fun getWeatherInfo(@Query("key") key: String, @Query("lat") lat: Double, @Query("lon") lon: Double): Single<WeatherInfo>
}