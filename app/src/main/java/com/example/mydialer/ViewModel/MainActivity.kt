package com.example.mydialer.ViewModel

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.mydialer.R
import timber.log.Timber
import timber.log.Timber.Forest.plant


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_contact, ContactFragment::class.java, null)
                .commit()
        }

        val findToolbar: Toolbar? = findViewById(R.id.find_toolbar)
        findToolbar?.let {
            setSupportActionBar(it)
        }

        plant(Timber.DebugTree())

        val textSearch: EditText? = findViewById(R.id.et_search)

        val getSharedPref = this.getPreferences(Context.MODE_PRIVATE)
        textSearch?.setText(getSharedPref.getString("SEARCH_FILTER", ""))

    }
}