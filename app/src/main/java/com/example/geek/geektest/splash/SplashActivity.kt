package com.example.geek.geektest.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.geek.geektest.R
import com.example.geek.geektest.map.MapActivity
import com.example.geek.geektest.models.WeatherInfo
import com.example.geek.geektest.weather_details.WeatherActivity
import com.google.gson.Gson

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var presenter: SplashContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashInjector().inject(this, this)
        presenter?.onViewReady()
    }

    override fun showWeather(weather: WeatherInfo) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra("info", Gson().toJson(weather))
        startActivity(intent)    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun goToMap() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}