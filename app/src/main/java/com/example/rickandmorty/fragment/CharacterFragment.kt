package com.example.rickandmorty.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.adapter.AdapterCharacter
import com.example.rickandmorty.model.adapter.DataModel
import com.example.rickandmorty.viewmodel.ModelFactory
import com.example.rickandmorty.viewmodel.VMCharacter


class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)

        val vmCharacter : VMCharacter? = ModelFactory.create(VMCharacter::class.java)

        val adapterCharacter = AdapterCharacter()


        val load_func = fun(){
            vmCharacter?.loadData(getAll = false, page = vmCharacter.next_page)
        }

        vmCharacter?.getData(getAll = false)?.observe(viewLifecycleOwner) { list ->

            val result : ArrayList<DataModel> = ArrayList()
            result.addAll(list.map {
                      DataModel.CharacterModel(
                         image = it.image,
                         name = it.name,
                         gender = it.gender,
                         episode = it.episode)
                })

            if (vmCharacter.info?.next != null)
                result.add(DataModel.LoadButtonModel(callback = load_func))

//            adapterCharacter.submitList()

            adapterCharacter.setData(result)
            adapterCharacter.notifyDataSetChanged()
        }

        val recyclerView: RecyclerView? = view.findViewById(R.id.r_view_character)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            this.adapter = adapterCharacter
        }


        return view
    }
}