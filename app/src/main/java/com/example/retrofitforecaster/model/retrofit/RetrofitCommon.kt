package com.example.retrofitforecaster.model.retrofit

object Common {

    private val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}