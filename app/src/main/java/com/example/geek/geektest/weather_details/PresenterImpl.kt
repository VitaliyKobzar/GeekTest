package com.example.geek.geektest.weather_details

import com.example.geek.geektest.base.extensions.runInBackground
import com.example.geek.geektest.database.DbEmulator
import com.example.geek.geektest.gateway.WeatherApi
import com.example.geek.geektest.models.WeatherInfo
import io.reactivex.disposables.CompositeDisposable

class PresenterImpl(
    private var view: WeatherContract.View?,
    private var api: WeatherApi?,
    private var db: DbEmulator?
) : WeatherContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewReady() {
        db?.let {
            compositeDisposable.add(
                it.getLastCoordinates()
                    .flatMap {
                        api?.getWeatherInfo(WeatherApi.apiKey, it.latitude, it.longitude)
                            ?.doOnSuccess { weather ->
                                view?.updateWeather(weather)
                                saveWeather(weather)
                            }
                    }.runInBackground()
                    ?.subscribe({
                        //TODO
                    }, {
                        //TODO
                    })
            )
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        view = null
        api = null
        db = null
    }

    override fun onChangePositionClick() {
        view?.showMap()
    }

    private fun saveWeather(weatherInfo: WeatherInfo) {
        db?.let {
            compositeDisposable.add(
                it.saveWeatherInfo(weatherInfo)
                    .runInBackground()
                    .subscribe({
                        //TODO
                    }, {
                        //TODO
                    })
            )
        }
    }

}