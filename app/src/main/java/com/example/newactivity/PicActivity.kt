package com.example.newactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class PicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_layout)

        title = "Картинка"

        val image: ImageView? = findViewById(R.id.picView)

        image?.let {
            Glide.with(this)
                .load(intent.getStringExtra("picLink"))
                .into(it)
        }

    }
}