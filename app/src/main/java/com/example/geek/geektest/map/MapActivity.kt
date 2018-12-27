package com.example.geek.geektest.map

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.example.geek.geektest.R
import com.example.geek.geektest.models.WeatherInfo
import com.example.geek.geektest.weather_details.WeatherActivity
import com.google.android.gms.maps.SupportMapFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MapActivity : FragmentActivity(), MapContract.View {

    private var presenter: MapContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MapInjector().inject(this, this)
        presenter?.onViewReady()
    }

    override fun setupMap(mapFragment: SupportMapFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, mapFragment).commit()
        showWeather.setOnClickListener { presenter?.onShowWeatherClick() }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun disableShowWeatherButton() {
        showWeather.isEnabled = false
    }

    override fun enableShowWeatherButton() {
        showWeather.isEnabled = true
    }

    override fun showWeatherInfo(weatherInfo: WeatherInfo) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra("info", Gson().toJson(weatherInfo))
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }
}
