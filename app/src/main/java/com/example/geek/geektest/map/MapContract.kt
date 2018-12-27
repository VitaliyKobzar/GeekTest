package com.example.geek.geektest.map

import com.example.geek.geektest.base.BasePresenter
import com.example.geek.geektest.models.WeatherInfo
import com.google.android.gms.maps.SupportMapFragment

interface MapContract {
    interface View {
        fun setupMap(mapFragment: SupportMapFragment)
        fun showWeatherInfo(weatherInfo: WeatherInfo)
        fun showMessage(message: String)
        fun disableShowWeatherButton()
        fun enableShowWeatherButton()

    }

    interface Presenter : BasePresenter {
        fun onShowWeatherClick()
    }
}