package com.example.recyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.module.Adapter
import com.example.recyclerview.module.CellClickListener
import com.example.recyclerview.module.ColorData


class MainActivity : AppCompatActivity(), CellClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView? = findViewById(R.id.rView)

        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = Adapter(this, fetchList(), this)
    }

    private fun fetchList() : ArrayList<ColorData> {

        val list = arrayListOf<ColorData>()

        for (i in 0..5) {
            val rndColor : Int = Color.rgb(
                (0..255).random(),
                (0..255).random(),
                (0..255).random())

            val colorData = ColorData(
                "Random Color: $rndColor",
                rndColor)

            list.add(colorData)
        }

        return list
    }

    override fun onCellClickListener(data: ColorData) {
        Toast.makeText(this,"IT'S ${data.colorName}", Toast.LENGTH_SHORT).show()
    }
}