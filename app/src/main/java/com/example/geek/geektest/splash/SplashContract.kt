package com.example.geek.geektest.splash

import com.example.geek.geektest.base.BasePresenter
import com.example.geek.geektest.models.WeatherInfo

class SplashContract {
    interface View{
        fun goToMap()
        fun showWeather(weather: WeatherInfo)
    }

    interface Presenter: BasePresenter
}