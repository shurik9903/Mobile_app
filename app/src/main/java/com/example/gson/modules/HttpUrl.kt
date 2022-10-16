package com.example.gson.modules

import com.example.gson.data.ImageData
import com.example.gson.data.Photo
import com.example.gson.data.PhotoPage
import com.example.gson.data.Wrapper
import com.google.gson.Gson
import okhttp3.*
import timber.log.Timber
import java.io.IOException

fun getURLDataOk(url : String, callback: (ArrayList<ImageData>) -> Unit) {

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

                val wrapper: Wrapper? = Gson().fromJson(body, Wrapper::class.java)
                val photoPage: PhotoPage? = Gson().fromJson(wrapper?.photos, PhotoPage::class.java)
                val photo: List<Photo> = Gson().fromJson(photoPage?.photo, Array<Photo>::class.java).toList()


                photo.forEachIndexed { index, element ->
                    run {
                        if ((index + 1) % 5 == 0)
                            Timber.d(element.toString())
                    }
                }

                val photoUrl: ArrayList<ImageData> = photo.map {  ImageData("https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}_z.jpg")} as ArrayList<ImageData>

                callback(photoUrl)
            }

        }
    })
}