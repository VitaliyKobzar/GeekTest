package com.example.geek.geektest.splash

import com.example.geek.geektest.base.extensions.runInBackground
import com.example.geek.geektest.database.DbEmulator
import io.reactivex.disposables.CompositeDisposable

class SplashPresenter(
    private var view: SplashContract.View?,
    private var db: DbEmulator?
) : SplashContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewReady() {
        db?.let {
            compositeDisposable.add(
                it.getLastWeatherInfo()
                    .runInBackground()
                    .subscribe({ weather ->
                        view?.showWeather(weather)
                    }, {
                        view?.goToMap()
                    })
            )
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        view = null
        db = null
    }

}