package com.example.mydialer.module

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mydialer.R
import com.example.mydialer.data.Contact


class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val c_name: TextView? = view.findViewById(R.id.textName)
    val c_phone: TextView? = view.findViewById(R.id.textPhone)
    val c_type: TextView? = view.findViewById(R.id.textType)

    fun bindTo(contact: Contact) {
        c_name?.text = contact.name
        c_phone?.text = contact.phone
        c_type?.text = contact.type

        this.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${c_phone?.text}")
            if (itemView.context is Activity){
                println("tel:$c_phone?.text")
                (itemView.context as Activity).startActivity(intent)
            }
        }
    }


}

class Adapter : ListAdapter<Contact, ViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

}