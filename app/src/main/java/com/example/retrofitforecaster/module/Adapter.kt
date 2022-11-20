package com.example.mydialer.module

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.data.WeatherData.WeatherElement

class Adapter(private val context: Context, private val weatherList: MutableList<WeatherElement>):RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img_temp: ImageView = itemView.findViewById(R.id.img_temp)
        val txt_temp: TextView = itemView.findViewById(R.id.text_temp)
        val txt_date: TextView = itemView.findViewById(R.id.text_date)

        fun bind(listItem: WeatherElement) {}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = when(viewType){
            0 -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot, parent, false)
            1 -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_cold, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot, parent, false)
        }
        return ViewHolder(itemView)
    }

    override fun getItemCount() = weatherList.size

    override fun getItemViewType(position: Int): Int {
        return if (weatherList[position].main.temp >= 0)
            0
        else
            1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = weatherList[position]
        holder.bind(listItem)


        holder.txt_date.text = weatherList[position].dt_txt
        holder.txt_temp.text = weatherList[position].main.temp.toString() + "\u2103"

    }

}