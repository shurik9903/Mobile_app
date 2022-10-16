package com.example.gson.modules

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gson.R
import com.example.gson.data.ImageData

class Adapter(private val context: Context,
              private val list: ArrayList<ImageData>,
              private val cellClickListener : CellClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image : ImageView?  = view.findViewById(R.id.imageView)
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
        holder.image?.let { Glide.with(context).load(data.imageUrl).into(it) }

        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(data)
        }
    }

}