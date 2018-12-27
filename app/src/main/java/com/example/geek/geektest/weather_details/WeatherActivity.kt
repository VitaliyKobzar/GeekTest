package com.example.geek.geektest.weather_details

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.geek.geektest.R
import com.example.geek.geektest.map.MapActivity
import com.example.geek.geektest.models.WeatherInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weather.*

class WeatherActivity : AppCompatActivity(), WeatherContract.View {

    private var presnter: WeatherContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        presnter = WeatherInjector().inject(this, this)
        changeCoordButton.setOnClickListener { presnter?.onChangePositionClick() }
        info.text = intent.getStringExtra("info")
    }

    override fun updateWeather(weather: WeatherInfo) {
        info.text = Gson().toJson(weather)
    }

    override fun showMap() {
        startActivity(Intent(this, MapActivity::class.java))
    }
}
