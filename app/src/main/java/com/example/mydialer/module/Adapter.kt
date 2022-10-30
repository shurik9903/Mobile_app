package com.example.mydialer.module

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