package com.example.rickandmorty.data

import com.example.rickandmorty.data.character.Info
import com.example.rickandmorty.data.character.Result

data class JCharacter(
    val info: Info,
    val results: List<Result>
)