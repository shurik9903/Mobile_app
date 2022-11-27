package com.example.retrofitforecaster.ViewModule

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.module.Adapter
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.data.API_key
import com.example.retrofitforecaster.data.JWeather
import com.example.retrofitforecaster.data.WeatherData.WeatherElement
import com.example.retrofitforecaster.data.WeatherStore
import com.example.retrofitforecaster.module.retrofit.Common
import com.example.retrofitforecaster.module.retrofit.RetrofitServices
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class WeatherFragment : Fragment() {

    var weatherData : String = ""
    var adapter: Adapter? = null
    var recyclerView : RecyclerView? = null

    var textSearch: EditText? = null
    var textNameCity : TextView? = null


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("weather", weatherData)
        Timber.i("save ${weatherData}")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        textSearch = activity?.findViewById(R.id.et_search)
        textNameCity = activity?.findViewById(R.id.name_city)

        recyclerView= view.findViewById(R.id.r_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)


        if (savedInstanceState != null) {
            weatherData = savedInstanceState.getString("weather").toString()

            if (weatherData.isNotEmpty()) {
                val jWeather = Gson().fromJson(weatherData, JWeather::class.java)
                val weatherList: MutableList<WeatherElement> =
                    jWeather.list as MutableList<WeatherElement>
                textNameCity?.text = jWeather.city.name
                adapter = Adapter()
                recyclerView?.adapter = adapter
                adapter?.submitList(weatherList)
            }
            Timber.i("load ${weatherData}")
        }

        val mService: RetrofitServices = Common.retrofitService

        textSearch?.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE ||
                (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) {

                mService.getWeather(city = textView.text.toString() + ",ru", key = API_key.getKey()).enqueue(object :
                    Callback<JWeather> {
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


                            adapter = Adapter()
                            adapter?.submitList(weatherList)
                            recyclerView?.adapter = adapter
                        } else {
                            textNameCity?.text = "Город не найден"
                            recyclerView?.adapter = null
                        }
                    }
                })

                true
            }else false }

        return view
    }

}