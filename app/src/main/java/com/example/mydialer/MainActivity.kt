package com.example.mydialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.data.ImageData
import com.example.mydialer.module.Adapter
import com.example.mydialer.module.getURLDataOk
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import timber.log.Timber.Forest.plant


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())

//        val toolbar: Toolbar  = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        val recyclerView: RecyclerView? = findViewById(R.id.rView)
        recyclerView?.layoutManager = GridLayoutManager(this, 2)

        var listContact = arrayListOf<ImageData>()
        val adapter = Adapter()
        recyclerView?.adapter = adapter
        adapter.submitList(listContact)

        val okAdapter = fun(data: ArrayList<ImageData>) {
            runOnUiThread {
                listContact = data
                adapter.submitList(listContact)
            }
        }


        getURLDataOk("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1", okAdapter)
    }



     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
            if(resultCode == RESULT_OK) {
                if (requestCode == 1 ) {
                    if (data!= null) {
                        val like : Boolean = data.getBooleanExtra("like",false)

                        if (like) {

                            val likeUrl: String? = data.getStringExtra("likeUrl")

                            val coordinatorLayout: RecyclerView? = findViewById(R.id.rView)

                            coordinatorLayout?.let {
                                val snackbar: Snackbar = Snackbar
                                    .make(
                                        it,
                                        "Картинка добавлена в избранное",
                                        Snackbar.LENGTH_LONG
                                    )
                                    .setAction("Открыть") {
                                        val browserIntent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(likeUrl)
                                        )
                                        startActivity(browserIntent)
                                    }
                                snackbar.show()
                            }
                        }
                    }
                }
            }
    }
}