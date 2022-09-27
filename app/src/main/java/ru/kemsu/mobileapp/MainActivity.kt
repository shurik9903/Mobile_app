package ru.kemsu.mobileapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2_3)

        //1 Задание 2 лаб
//        val m_button:Button? = findViewById<Button>(R.id.button_ok)
//
//        m_button?.let {
//            m_button.setOnClickListener {
//                Toast.makeText(applicationContext, "Кнопка ОК", Toast.LENGTH_SHORT).show()
//            }
//        }

        //2 Задание 2 лаб
/*        val but_log:Button? = findViewById<Button>(R.id.button_log)
        val edit_text:EditText? = findViewById<EditText>(R.id.edit_text)

        but_log?.let {
            but_log.setOnClickListener {
                edit_text?.let {
                    Log.v("From EditText", edit_text.text.toString())
                }
            }
        }

        val but_timber:Button? = findViewById<Button>(R.id.button_timber)
        Timber.plant(Timber.DebugTree())

        but_timber?.let {
            but_timber.setOnClickListener {
                edit_text?.let {
                    Timber.v(edit_text.text.toString())
                }
            }
        }*/


        //3 Задание 2 лаб

        val user_text:EditText? = findViewById<EditText>(R.id.user_text)

        val bBlack:Button? = findViewById<Button>(R.id.black_text)
        val bRed:Button? = findViewById<Button>(R.id.red_text)
        val bSize8:Button? = findViewById<Button>(R.id.size_8)
        val bSize24:Button? = findViewById<Button>(R.id.size_24)
        val bBackWhite:Button? = findViewById<Button>(R.id.white_back)
        val bBackYellow:Button? = findViewById<Button>(R.id.yellow_back)

        bBlack?.let {
            bBlack.setOnClickListener {
                user_text?.let {
                    user_text.setTextColor(Color.BLACK)
                }
            }
        }

        bRed?.let {
            bRed.setOnClickListener {
                user_text?.let {
                    user_text.setTextColor(Color.RED)
                }
            }
        }

        bSize8?.let {
            bSize8.setOnClickListener {
                user_text?.let {
                    user_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8.0F)
                }
            }
        }

        bSize24?.let {
            bSize24.setOnClickListener {
                user_text?.let {
                    user_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0F)
                }
            }
        }

        bBackWhite?.let {
            bBackWhite.setOnClickListener {
                user_text?.let {
                    user_text.setBackgroundColor(Color.WHITE)
                }
            }
        }

        bBackYellow?.let {
            bBackYellow.setOnClickListener {
                user_text?.let {
                    user_text.setBackgroundColor(Color.YELLOW)
                }
            }
        }

    }
}