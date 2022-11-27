package com.example.mydialer.module

import com.google.gson.Gson
import okhttp3.*
import com.example.mydialer.data.Contact
import timber.log.Timber
import java.io.IOException

fun getURLDataOk(url : String, callback: (ArrayList<Contact>) -> Unit) {

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

                val body = response.body?.string()
                val contacts: List<Contact> = Gson().fromJson(body, Array<Contact>::class.java).toList()


                Timber.i(contacts.toString())

                callback(contacts as ArrayList<Contact>)
            }

        }
    })
}