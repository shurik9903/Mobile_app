package com.example.retrofitforecaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.module.Adapter
import com.example.retrofitforecaster.data.API_key
import com.example.retrofitforecaster.data.JWeather
import com.example.retrofitforecaster.data.WeatherData.WeatherElement
import com.example.retrofitforecaster.data.WeatherStore
import com.example.retrofitforecaster.module.retrofit.Common
import com.example.retrofitforecaster.module.retrofit.RetrofitServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {

    var weatherData : String = ""
    lateinit var adapter: Adapter
    var recyclerView: RecyclerView? = null
    var textNameCity : TextView? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("weather", weatherData)
        Timber.i("save ${weatherData}")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        weatherData = savedInstanceState.getString("weather").toString()

        if (weatherData.isNotEmpty()){
            val jWeather = Gson().fromJson(weatherData, JWeather::class.java)
            val weatherList: MutableList<WeatherElement> = jWeather.list as MutableList<WeatherElement>
            textNameCity?.text = jWeather.city.name
            adapter = Adapter(baseContext, weatherList)
            adapter.notifyDataSetChanged()
            recyclerView?.adapter = adapter
        }
        Timber.i("load ${weatherData}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())

        Timber.i("WeatherStore ${WeatherStore.weathers}")

        val findToolbar: Toolbar? = findViewById(R.id.find_toolbar)
        findToolbar?.let {
            setSupportActionBar(it)
        }

        val textSearch: EditText? = findViewById(R.id.et_search)
        textNameCity = findViewById(R.id.name_city)

        recyclerView = findViewById(R.id.r_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.setHasFixedSize(true)

        val mService: RetrofitServices = Common.retrofitService

        textSearch?.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE ||
            (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) {

                mService.getWeather(city = textView.text.toString() + ",ru", key = API_key.getKey()).enqueue(object : Callback<JWeather> {
                    override fun onFailure(call: Call<JWeather>, t: Throwable) {
                        Timber.e("Error: ${t.message}")
                    }

                    override fun onResponse(call: Call<JWeather>, response: Response<JWeather>) {

                        Timber.i("JSON: ${response.body()}")

                        if (response.body() is JWeather){


                            val jWeather = response.body() as JWeather
                            val weatherList: MutableList<WeatherElement> = jWeather.list as MutableList<WeatherElement>

                            textNameCity?.text = jWeather.city.name
                            weatherData = Gson().toJson(jWeather)
                            WeatherStore.weathers = weatherList.toList()


                            adapter = Adapter(baseContext, weatherList)
                            adapter.notifyDataSetChanged()
                            recyclerView?.adapter = adapter
                        } else {
                            textNameCity?.text = "Город не найден"
                            recyclerView?.adapter = null
                        }
                    }
                })

                true
        }else false }

    }

}