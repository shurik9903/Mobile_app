package com.example.mydialer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        plant(Timber.DebugTree())

        val button: Button? = findViewById(R.id.btn_search)
        val textSearch: EditText? = findViewById(R.id.et_search)



        val recyclerView: RecyclerView? = findViewById(R.id.rView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        var listContact = arrayListOf<Contact>()
        val adapter = Adapter()
        recyclerView?.adapter = adapter
        adapter.submitList(listContact)

        button?.setOnClickListener {
            if (textSearch?.text?.isEmpty() != true){
                val findList = listContact.filter {
                    it.name.contains(textSearch?.text.toString()) or
                            it.phone.contains(textSearch?.text.toString()) or
                            it.type.contains(textSearch?.text.toString())
                }

                adapter.submitList(findList)
            } else {
                adapter.submitList(listContact)
            }
        }


        val okAdapter = fun(data: ArrayList<Contact>) {
            runOnUiThread {
                listContact = data
                adapter.submitList(listContact)
            }
        }

        getURLDataOk("https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download", okAdapter)
    }
}