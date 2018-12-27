package com.example.geek.geektest.weather_details

import android.content.Context
import com.example.geek.geektest.database.DbEmulator
import com.example.geek.geektest.gateway.Gateway
import com.example.geek.geektest.gateway.WeatherApi

class WeatherInjector {
    fun inject(view: WeatherContract.View, context: Context): WeatherContract.Presenter {
        return PresenterImpl(view,
            Gateway.create(WeatherApi::class.java),
            DbEmulator.getInstance(context))
    }

}
