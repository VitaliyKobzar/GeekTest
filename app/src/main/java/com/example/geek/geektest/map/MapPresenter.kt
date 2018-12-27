package com.example.geek.geektest.map

import com.example.geek.geektest.base.extensions.runInBackground
import com.example.geek.geektest.database.DbEmulator
import com.example.geek.geektest.gateway.WeatherApi
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.disposables.CompositeDisposable

class MapPresenter(
    private var view: MapContract.View?,
    private var db: DbEmulator,
    private var api: WeatherApi
) : MapContract.Presenter, OnMapReadyCallback {

    private val mapFragment: SupportMapFragment = SupportMapFragment()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewReady() {
        view?.setupMap(mapFragment)
        mapFragment.getMapAsync(this)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        view = null
    }

    override fun onMapReady(map: GoogleMap?) {
        map?.let { nonNullMap ->
            nonNullMap.setOnMapClickListener { coordinates ->
                nonNullMap.clear()
                nonNullMap.addMarker(MarkerOptions().position(coordinates))
                compositeDisposable.add(
                    db?.saveCoordinates(LatLng(coordinates.latitude, coordinates.longitude))
                        .runInBackground()
                        .subscribe({}, {})
                )
                compositeDisposable.add(
                    api.getWeatherInfo(WeatherApi.apiKey, coordinates.latitude, coordinates.longitude)
                        .flatMapCompletable { db.saveWeatherInfo(it) }
                        .runInBackground()
                        .doOnSubscribe { view?.disableShowWeatherButton() }
                        .doOnTerminate { view?.enableShowWeatherButton()}
                        .subscribe({
                            //TODO
                        }, {
                            //TODO
                        })
                )
            }
        }
    }

    override fun onShowWeatherClick() {
        db.getLastWeatherInfo()
            .runInBackground()
            .subscribe({
                view?.showWeatherInfo(it)
            }, {
                view?.showMessage("Select coordinates")
            })
    }
}