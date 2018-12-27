package com.example.geek.geektest.weather_details

import com.example.geek.geektest.base.BasePresenter
import com.example.geek.geektest.models.WeatherInfo

interface WeatherContract {
    interface View{
        fun showMap()
        fun updateWeather(weather: WeatherInfo)
    }

    interface Presenter: BasePresenter{
        fun onChangePositionClick()
    }
}