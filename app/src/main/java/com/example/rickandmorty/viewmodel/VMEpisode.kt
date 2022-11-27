package com.example.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitforecaster.module.retrofit.Common
import com.example.retrofitforecaster.module.retrofit.RetrofitServices
import com.example.rickandmorty.data.JEpisode
import com.example.rickandmorty.data.character.Result

import com.example.rickandmorty.data.episode.Result as Episode
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class VMEpisode : ViewModel(){

    var episodeData : MutableLiveData<ArrayList<Episode>>? = null
    var allEpisode : ArrayList<Episode> = ArrayList()

    fun getData(page : Int? = null, getAll : Boolean? = null): MutableLiveData<ArrayList<Episode>>? {

        if (episodeData == null){
            episodeData = MutableLiveData<ArrayList<Episode>>()
            allEpisode = ArrayList()
            loadData(page = page?: 1, getAll = getAll?: true)
        }

        return episodeData
    }

    fun loadData(page : Int, getAll : Boolean){

        val mService: RetrofitServices = Common.retrofitService

        mService.getEpisode(page = page).enqueue(object :
            Callback<JEpisode> {
            override fun onFailure(call: Call<JEpisode>, t: Throwable) {
                Timber.e("Error: ${t.message}")
                episodeData?.postValue(null)
            }

            override fun onResponse(call: Call<JEpisode>, response: Response<JEpisode>) {

                Timber.i("JSON: ${response.body()}")

                if (response.body() is JEpisode){

                    val jEpisode = response.body() as JEpisode


                    if (getAll && jEpisode.info.next != null){
                        allEpisode.addAll(jEpisode.results as ArrayList<Episode>)
                        loadData(page + 1, true)
                    }else if (getAll && jEpisode.info.next == null)  {
                        allEpisode.addAll(jEpisode.results as ArrayList<Episode>)
                        episodeData?.postValue(allEpisode)
                    } else if(!getAll) {
                        episodeData?.postValue(jEpisode.results as ArrayList<Episode>)
                    }

                } else {
                    episodeData?.postValue(null)
                }
            }
        })

    }


}