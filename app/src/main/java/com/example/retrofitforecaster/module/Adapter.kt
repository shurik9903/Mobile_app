package com.example.mydialer.module

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitforecaster.R
import com.example.retrofitforecaster.data.JWeather
import com.example.retrofitforecaster.data.WeatherData.WeatherElement

//
//class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
////    val c_name: TextView? = view.findViewById(R.id.textName)
////    val c_phone: TextView? = view.findViewById(R.id.textPhone)
////    val c_type: TextView? = view.findViewById(R.id.textType)
//
//    fun bindTo(weather: JWeather) {
////        c_name?.text = contact.name
////        c_phone?.text = contact.phone
////        c_type?.text = contact.type
//
////        this.itemView.setOnClickListener {
////            val intent = Intent(Intent.ACTION_DIAL)
////            intent.data = Uri.parse("tel:${c_phone?.text}")
////            if (itemView.context is Activity){
////                println("tel:$c_phone?.text")
////                (itemView.context as Activity).startActivity(intent)
////            }
////        }
//    }
//
//
//}
//
//class Adapter : ListAdapter<JWeather, ViewHolder>(ContactDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
//        return ViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.rview_item, parent, false)
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindTo(getItem(position))
//    }
//
//}

class ViewHolderHot(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(weather: JWeather) {

    }

}

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