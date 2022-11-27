package com.example.rickandmorty.model.retrofit.services

import com.example.rickandmorty.data.JCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterServices {

    enum class Status(val status: String) {
        ALIVE("alive"),
        DEAD("dead"),
        UNKNOWN("unknown")
    }

    enum class Gender(val gender: String){
        FEMALE("female"),
        MALE("male"),
        GENDERLESS("genderless"),
        UNKNOWN("unknown")
    }

    @GET("character")
    fun getCharacter(@Query("page") page: Int? = null,
                     @Query("id") id: Int? = null,
                     @Query("name") name: String? = null,
                     @Query("status") status: Status? = null,
                     @Query("species") species: String? = null,
                     @Query("type") type: String? = null,
                     @Query("gender") gender: Gender? = null,
    ): Call<JCharacter>

}