package com.example.geek.geektest.database

import android.content.Context
import com.example.geek.geektest.models.WeatherInfo
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Completable
import io.reactivex.Single

interface DbEmulator {
    fun getLastCoordinates(): Single<LatLng>
    fun saveCoordinates(coordinates: LatLng): Completable

    fun saveWeatherInfo(info: WeatherInfo):Completable
    fun getLastWeatherInfo(): Single<WeatherInfo>

    companion object {
        fun getInstance(context: Context): DbEmulator =
            DbEmulatorImpl(context.getSharedPreferences("pref", Context.MODE_PRIVATE))

    }
}