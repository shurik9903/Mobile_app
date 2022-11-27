package com.example.mydialer.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.data.WeatherData.WeatherElement


class WeatherDiffCallback: DiffUtil.ItemCallback<WeatherElement>() {
    override fun areContentsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: WeatherElement, newItem: WeatherElement): Boolean {
        return oldItem == newItem
    }


}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img_temp: ImageView = itemView.findViewById(R.id.img_temp)
    val txt_temp: TextView = itemView.findViewById(R.id.text_temp)
    val txt_date: TextView = itemView.findViewById(R.id.text_date)

    fun bind(weatherElement: WeatherElement) {
        txt_date.text = weatherElement.dt_txt
        txt_temp.text = weatherElement.main.temp.toString() + "\u2103"
    }

}

class Adapter : ListAdapter<WeatherElement, ViewHolder>(WeatherDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View = when(viewType){
            0 -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot, parent, false)
            1 -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_cold, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.rview_item_hot, parent, false)
        }
        return ViewHolder(itemView)
    }


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).main.temp >= 0)
            0
        else
            1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
