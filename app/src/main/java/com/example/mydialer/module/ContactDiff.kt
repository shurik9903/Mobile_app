package com.example.mydialer.module

import androidx.recyclerview.widget.DiffUtil
import com.example.mydialer.data.ImageData

class ContactDiffCallback: DiffUtil.ItemCallback<ImageData>() {
    override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem.imageUrl == newItem.imageUrl
    }

    override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
        return oldItem == newItem
    }
}