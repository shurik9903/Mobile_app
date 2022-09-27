package com.example.nestedlayouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number:Int = 1;

        val but_roll:Button? = findViewById<Button>(R.id.button_roll)

        val layout1:LinearLayout? = findViewById<LinearLayout>(R.id.My_Layout_1)
        val layout2:LinearLayout? = findViewById<LinearLayout>(R.id.My_Layout_2)
        val layout3:ConstraintLayout? = findViewById<ConstraintLayout>(R.id.My_Layout_3)

        var lay_iter_1 = layout1?.children?.iterator()
        var lay_iter_2 = layout2?.children?.iterator()
        var lay_iter_3 = layout3?.children?.iterator()

        lay_iter_1?.next()
        lay_iter_2?.next()
        lay_iter_3?.next()

        but_roll?.setOnClickListener{

            number++

            layout1?.children?.forEach { if(it is TextView) it.text = ""}
            layout2?.children?.forEach { if(it is TextView) it.text = ""}
            layout3?.children?.forEach { if(it is TextView) it.text = ""}

            if (lay_iter_1?.hasNext() == false) lay_iter_1 = layout1?.children?.iterator()
            lay_iter_1?.next()?.let { if (it is TextView) it.text = number.toString() }

            if (lay_iter_2?.hasNext() == false) lay_iter_2 = layout2?.children?.iterator()
            lay_iter_2?.next()?.let { if (it is TextView) it.text = number.toString() }

            if (lay_iter_3?.hasNext() == false) lay_iter_3 = layout3?.children?.iterator()
            lay_iter_3?.next()?.let { if (it is TextView) it.text = number.toString() }



        }

    }
}