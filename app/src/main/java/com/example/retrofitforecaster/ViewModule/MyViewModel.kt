package com.example.retrofitforecaster.ViewModule

import androidx.lifecycle.ViewModel
import com.example.retrofitforecaster.data.API_key
import com.example.retrofitforecaster.data.JWeather
import com.example.retrofitforecaster.data.WeatherData.WeatherElement
import com.example.retrofitforecaster.module.retrofit.Common
import com.example.retrofitforecaster.module.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import kotlinx.coroutines.*

class MyViewModel : ViewModel() {

    var weatherData : List<WeatherElement>? = null
    var city : String? = null

    fun getData(cityName: String? = null, callback: (List<WeatherElement>?) -> Unit) {

        if(weatherData == null || cityName != null){
            loadData(cityName, callback)
        }else{
            callback(weatherData)
        }

    }

    private fun loadData(cityName: String?, callback: (List<WeatherElement>?) -> Unit){

        city = cityName

        val mService: RetrofitServices = Common.retrofitService

        mService.getWeather(city = cityName + ",ru", key = API_key.getKey()).enqueue(object :
            Callback<JWeather> {
                    override fun onFailure(call: Call<JWeather>, t: Throwable) {
                        Timber.e("Error: ${t.message}")
                        weatherData = null
                        callback(weatherData)
                    }

                    override fun onResponse(call: Call<JWeather>, response: Response<JWeather>) {

                        Timber.i("JSON: ${response.body()}")

                        if (response.body() is JWeather){

                            val jWeather = response.body() as JWeather

                            weatherData = jWeather.list as MutableList<WeatherElement>
                            callback(weatherData)
                        } else {
                            weatherData = null
                            callback(weatherData)
                        }
                    }
                })


    }


}