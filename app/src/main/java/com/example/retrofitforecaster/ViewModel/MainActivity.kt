package com.example.retrofitforecaster.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.retrofitforecaster.R
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.weather_fragment, WeatherFragment::class.java , null)
                .commit()
        }
        plant(Timber.DebugTree())

        val findToolbar: Toolbar? = findViewById(R.id.find_toolbar)
        findToolbar?.let {
            setSupportActionBar(it)
        }

    }

}