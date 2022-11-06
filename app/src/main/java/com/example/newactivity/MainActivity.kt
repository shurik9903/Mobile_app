package com.example.newactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnShowPic: Button? = findViewById(R.id.btn_show_pic)

        btnShowPic?.setOnClickListener {
            val intent = Intent(this, PicActivity::class.java)
            intent.putExtra("picLink", "https://www.gctc.ru/media/images/education/general/cosmos_1.jpg")
            startActivity(intent);
        }

    }
}