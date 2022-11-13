package com.example.mydialer.module

import androidx.recyclerview.widget.DiffUtil
import com.example.mydialer.data.Contact

class ContactDiffCallback: DiffUtil.ItemCallback<Contact>() {
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}