package com.example.rickandmorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitforecaster.module.retrofit.Common
import com.example.retrofitforecaster.module.retrofit.RetrofitServices
import com.example.rickandmorty.data.JCharacter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import com.example.rickandmorty.data.character.Result as Character
import com.example.rickandmorty.data.character.Info

class VMCharacter : ViewModel(){

    var characterData : MutableLiveData<ArrayList<Character>>? = null
    var allCharacter : ArrayList<Character> = ArrayList()
    var info : Info? = null
    var next_page : Int = 1

    fun getData(page : Int? = null, getAll : Boolean? = null): MutableLiveData<ArrayList<Character>>? {

        if (characterData == null){
            characterData = MutableLiveData<ArrayList<Character>>()
            allCharacter = ArrayList()
            loadData(page = page?: -1, getAll = getAll?: true)
        }

        return characterData
    }

    fun loadData(page : Int, getAll : Boolean){

        val mService: RetrofitServices = Common.retrofitService

        mService.getCharacter(page = page).enqueue(object :
            Callback<JCharacter> {
            override fun onFailure(call: Call<JCharacter>, t: Throwable) {
                Timber.e("Error: ${t.message}")
                characterData?.postValue(null)
            }

            override fun onResponse(call: Call<JCharacter>, response: Response<JCharacter>) {

                Timber.i("JSON: ${response.body()}")

                if (response.body() is JCharacter){

                    val jCharacter = response.body() as JCharacter

                    if (getAll && jCharacter.info.next != null){
                        allCharacter.addAll(jCharacter.results as ArrayList<Character>)
                        loadData(page + 1, true)
                    }else if (getAll && jCharacter.info.next == null)  {
                        allCharacter.addAll(jCharacter.results as ArrayList<Character>)
                        characterData?.postValue(allCharacter)
                    } else if(!getAll && page != -1) {
                        info = jCharacter.info
                        next_page = page + 1
                        allCharacter.addAll(jCharacter.results as ArrayList<Character>)
                        characterData?.postValue(allCharacter)
                    } else if (!getAll){
                        info = jCharacter.info
                        characterData?.postValue(jCharacter.results as ArrayList<Character>)
                    }

                } else {
                    characterData?.postValue(null)
                }
            }
        })

    }



}