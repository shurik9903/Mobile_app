package com.example.mydialer


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class PicViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_viewer)

        val image: ImageView? = findViewById(R.id.pic_image)

        image?.let {
            Glide.with(this)
                .load(intent.getStringExtra("picLink"))
                .into(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolab_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            R.id.like -> {
//                item.icon = AppCompatResources.getDrawable(this, R.drawable.ic_baseline_favorite)
//                Toast.makeText(this, "Добавлено в Избранное", Toast.LENGTH_SHORT).show()
                val intentOut = Intent()
                intentOut.putExtra("like", true)
                intentOut.putExtra("likeUrl", intent.getStringExtra("picLink"))
                setResult(RESULT_OK, intentOut)
                finish()
            }
        }

        return true
    }
}