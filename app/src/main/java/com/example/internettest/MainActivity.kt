package com.example.internettest


import android.os.Bundle

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.internettest.module.getURLData
import com.example.internettest.module.getURLDataOk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHTTP : Button? = findViewById(R.id.btnHTTP)

        btnHTTP?.setOnClickListener{
            Thread {
                getURLData(URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"))
            }.start()
        }

        val btnOkHTTP : Button? = findViewById(R.id.btnOkHTTP)

        btnOkHTTP?.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO) {
                getURLDataOk("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
            }
        }

    }
}