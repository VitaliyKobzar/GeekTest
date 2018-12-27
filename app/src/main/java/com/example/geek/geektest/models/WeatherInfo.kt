package com.example.geek.geektest.models

import com.google.gson.annotations.SerializedName



data class WeatherInfo(
@SerializedName("data") var data: List<DataItem>? = null,
@SerializedName("count") var count: Int? = null
)