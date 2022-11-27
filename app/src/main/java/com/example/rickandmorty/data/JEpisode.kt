package com.example.rickandmorty.data

import com.example.rickandmorty.data.episode.Info
import com.example.rickandmorty.data.episode.Result

data class JEpisode(
    val info: Info,
    val results: List<Result>
)