package com.example.rickandmorty.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmorty.fragment.CharacterFragment
import com.example.rickandmorty.R
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_character, CharacterFragment::class.java , null)
                .commit()
        }

    }
}