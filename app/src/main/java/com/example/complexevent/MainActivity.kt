package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user_text:EditText? = findViewById<EditText>(R.id.TextField)
        val check_select:CheckBox? = findViewById<CheckBox>(R.id.CheckSelect)
        val button_ok:Button? = findViewById<Button>(R.id.ButtonOK)
        val text_out:TextView? = findViewById<TextView>(R.id.TextOut)
        val progress:ProgressBar? = findViewById<ProgressBar>(R.id.Progress)

        button_ok?.setOnClickListener{
            if (check_select?.isChecked == true){
                text_out?.text = user_text?.text
                progress?.incrementProgressBy(10)
            }
        }
    }
}