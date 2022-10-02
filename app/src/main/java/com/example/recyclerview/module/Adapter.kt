package com.example.recyclerview.module

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class Adapter(private val context: Context,
              private val list: ArrayList<ColorData>,

              private val cellClickListener : CellClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val color : View?  = view.findViewById(R.id.colorView)
        val text : TextView? = view.findViewById(R.id.colorText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.color?.setBackgroundColor(data.colorHex);
        holder.text?.text = data.colorName;

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }

}