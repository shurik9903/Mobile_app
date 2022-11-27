package com.example.retrofitforecaster.module.retrofit

import com.example.retrofitforecaster.data.JWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("forecast?units=metric")
    fun getWeather(@Query("q") city: String,
                   @Query("appid") key: String
    ): Call<JWeather>

}