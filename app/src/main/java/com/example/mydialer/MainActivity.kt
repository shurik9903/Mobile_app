package com.example.mydialer


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.EditText
import androidx.appcompat.widget.Toolbar

import androidx.core.widget.doAfterTextChanged

import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.data.Contact
import com.example.mydialer.module.Adapter
import com.example.mydialer.module.getURLDataOk
import timber.log.Timber
import timber.log.Timber.Forest.plant

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findToolbar: Toolbar? = findViewById(R.id.find_toolbar)
        findToolbar?.let {
            setSupportActionBar(it)
        }

        plant(Timber.DebugTree())


        val textSearch: EditText? = findViewById(R.id.et_search)

        val recyclerView: RecyclerView? = findViewById(R.id.rView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val adapter = Adapter()
        recyclerView?.adapter = adapter

        var listContact = arrayListOf<Contact>()
        val findList = MutableLiveData<List<Contact>>()

        findList.observe(this) { list ->
            adapter.submitList(list)
        }

        textSearch?.doAfterTextChanged {

            findList.value = listContact.filter { contact ->
                contact.name.contains(it.toString(), true) or
                contact.phone.contains(it.toString(), true) or
                contact.type.contains(it.toString(), true) or
                it.toString().isEmpty()
            }


            val setSharedPref = this.getPreferences(Context.MODE_PRIVATE)
            with (setSharedPref.edit()) {
                putString("SEARCH_FILTER", it.toString())
                apply()
            }
        }

        val getSharedPref = this.getPreferences(Context.MODE_PRIVATE)
        textSearch?.setText(getSharedPref.getString("SEARCH_FILTER", ""))

        val okAdapter = fun(data: ArrayList<Contact>) {
            runOnUiThread {
                listContact = data
                findList.value = data
            }
        }

        getURLDataOk("https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download", okAdapter)
    }
}