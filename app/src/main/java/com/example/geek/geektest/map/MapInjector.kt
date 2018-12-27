package com.example.geek.geektest.map

import android.content.Context
import com.example.geek.geektest.database.DbEmulator
import com.example.geek.geektest.gateway.Gateway
import com.example.geek.geektest.gateway.WeatherApi

class MapInjector {

    fun inject(view: MapContract.View,
               context: Context): MapContract.Presenter {
        return MapPresenter(view, DbEmulator.getInstance(context), Gateway.create(WeatherApi::class.java))
    }
}