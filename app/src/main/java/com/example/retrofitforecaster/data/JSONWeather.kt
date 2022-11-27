package com.example.retrofitforecaster.data

import com.example.retrofitforecaster.data.WeatherData.City
import com.example.retrofitforecaster.data.WeatherData.WeatherElement

data class JWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherElement>,
    val message: Int
)

object  WeatherStore {
    var weathers: List<WeatherElement> = emptyList()
}