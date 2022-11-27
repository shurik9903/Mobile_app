package com.example.rickandmorty.model.retrofit.services

import com.example.rickandmorty.data.JEpisode
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeServices {
    @GET("episode")
    fun getEpisode(@Query("id") id: Int? = null,
                   @Query("name") name: String? = null,
                   @Query("episode") episode: String? = null,
                   @Query("page") page: Int? = null,
    ): Call<JEpisode>
}