package com.example.retrofitforecaster.ViewModule

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.module.Adapter
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.data.WeatherData.WeatherElement


class WeatherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        val myViewModel : MyViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        val textSearch : EditText? = activity?.findViewById(R.id.et_search)
        val textNameCity : TextView? = activity?.findViewById(R.id.name_city)

        val recyclerView: RecyclerView? = view.findViewById(R.id.r_view)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)

        val adapter = Adapter()
        recyclerView?.adapter = adapter

        if (savedInstanceState != null) {
            myViewModel.getData(callback =  fun(data: List<WeatherElement>?) {
                adapter.submitList(data)
            })
            textNameCity?.text = myViewModel.city
        }


        textSearch?.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE ||
                (keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) {

                myViewModel.getData(textView.text.toString(), fun(data: List<WeatherElement>?) {
                    adapter.submitList(data)
                })

                textNameCity?.text = textView.text.toString()

                true
            }else { false }}

        return view
    }

}