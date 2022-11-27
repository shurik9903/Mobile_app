package com.example.rickandmorty.viewmodel

import androidx.lifecycle.ViewModel

object ModelFactory {
    fun <T : ViewModel> create(modelClass: Class<T>): T? {
        return when (modelClass){
            VMCharacter::class.java -> VMCharacter() as T
            VMEpisode::class.java -> VMEpisode() as T
            else -> null
        }
    }
}