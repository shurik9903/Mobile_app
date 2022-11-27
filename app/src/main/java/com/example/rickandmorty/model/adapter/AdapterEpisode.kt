package com.example.rickandmorty.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.rickandmorty.R
import com.example.rickandmorty.data.episode.Result as Episode

class EpisodeDiffCallback: DiffUtil.ItemCallback<Episode>() {
    override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean {
        return oldItem == newItem
    }

}

class EpisodeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txt_episode_name : TextView? = itemView.findViewById(R.id.name_episode)
    val txt_episode_date : TextView? = itemView.findViewById(R.id.date_episode)


    fun bind(episode: Episode) {

        txt_episode_name?.text = episode.name
        txt_episode_date?.text = episode.air_date
    }

}

class AdapterEpisode : ListAdapter<Episode, EpisodeViewHolder>(EpisodeDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

}