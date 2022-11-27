package com.example.retrofitforecaster.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitforecaster.data.API_key
import com.example.retrofitforecaster.data.JWeather
import com.example.retrofitforecaster.data.WeatherData.WeatherElement
import com.example.retrofitforecaster.model.retrofit.Common
import com.example.retrofitforecaster.model.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MyViewModel : ViewModel() {

    var weatherData : MutableLiveData<List<WeatherElement>>? = null
    var city : String? = null

    fun getData(cityName: String? = null) :  MutableLiveData<List<WeatherElement>>? {


        if(weatherData == null){
            weatherData = MutableLiveData<List<WeatherElement>>()
        }
        if(cityName != null) {
            loadData(cityName)
        }
        return weatherData

    }

    private fun loadData(cityName: String?){

        city = cityName

        val mService: RetrofitServices = Common.retrofitService

        mService.getWeather(city = cityName + ",ru", key = API_key.getKey()).enqueue(object :
            Callback<JWeather> {
                    override fun onFailure(call: Call<JWeather>, t: Throwable) {
                        Timber.e("Error: ${t.message}")
                        weatherData?.postValue(null)
                    }

                    override fun onResponse(call: Call<JWeather>, response: Response<JWeather>) {

                        Timber.i("JSON: ${response.body()}")

                        if (response.body() is JWeather){

                            val jWeather = response.body() as JWeather

                            weatherData?.postValue(jWeather.list)
                        } else {
                            weatherData?.postValue(null)
                        }
                    }
                })


    }


}