package com.example.internettest.module

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.WorkerThread
import okhttp3.*
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


@WorkerThread
fun getURLData(url: URL){

    val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

    try {
        urlConnection.apply {
            connectTimeout = 10000
            requestMethod = "GET"
            doInput = true //inputStream
        }

        if (urlConnection.responseCode != HttpURLConnection.HTTP_OK){
            Log.d("Flickr cats: |Connecting Error: ", urlConnection.responseCode.toString())
            return
        }

        val streamReader = InputStreamReader(urlConnection.inputStream)

        var data : String = ""
        streamReader.use{
            data = it.readText()
        }

        Log.d("Flickr cats: ", data)

    } catch (ex : Exception) {
        Log.d("Flickr cats: |Connecting Error: ", ex.toString())
    } finally {
        urlConnection.disconnect()
    }
}

fun getURLDataOk(url : String) {

    val client = OkHttpClient();

    val request : Request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                Log.i("Flickr OkCats: ", response.body?.string().toString())
            }

        }
    })
}