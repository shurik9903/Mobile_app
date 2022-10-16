package com.example.gson

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gson.data.ImageData
import com.example.gson.modules.Adapter
import com.example.gson.modules.CellClickListener
import com.example.gson.modules.getURLDataOk
import timber.log.Timber
import timber.log.Timber.Forest.plant


class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView? = findViewById(R.id.rView)

        recyclerView?.layoutManager = GridLayoutManager(this, 2);

        plant(Timber.DebugTree())

        val okAdapter = fun(data: ArrayList<ImageData>) {
            runOnUiThread {
                recyclerView?.adapter = Adapter(this, data, this)
            }
        }

        getURLDataOk("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1", okAdapter)
    }

    override fun onCellClickListener(data: ImageData) {
        val myClipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val myClip: ClipData = ClipData.newPlainText("text", data.imageUrl)
        myClipboard.setPrimaryClip(myClip)

        Timber.i(data.imageUrl)
    }
}