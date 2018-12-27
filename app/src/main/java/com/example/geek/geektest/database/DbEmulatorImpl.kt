package com.example.geek.geektest.database

import android.content.SharedPreferences
import android.text.TextUtils
import com.example.geek.geektest.models.WeatherInfo
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single


class DbEmulatorImpl(private val pref: SharedPreferences) : DbEmulator {

    private val coordKey = "coord"
    private val infoKey = "info"

    override fun getLastCoordinates(): Single<LatLng> = Single.fromCallable {
        val raw = pref.getString(coordKey, "")
        if (!TextUtils.isEmpty(raw)) {
            Gson().fromJson(raw, LatLng::class.java)
        } else null
    }

    override fun saveCoordinates(coordinates: LatLng): Completable = Completable.fromAction {
        val ed = pref.edit()
        ed.putString(coordKey, Gson().toJson(coordinates))
        ed.commit()
    }

    override fun saveWeatherInfo(info: WeatherInfo): Completable = Completable.fromAction {
        val ed = pref.edit()
        ed.putString(infoKey, Gson().toJson(info))
        ed.commit()
    }

    override fun getLastWeatherInfo(): Single<WeatherInfo> = Single.fromCallable {
        val raw = pref.getString(infoKey, "")
        if (!TextUtils.isEmpty(raw)) {
            Gson().fromJson(raw, WeatherInfo::class.java)
        } else null
    }
}