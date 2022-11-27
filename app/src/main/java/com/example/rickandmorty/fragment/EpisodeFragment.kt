package com.example.rickandmorty.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.model.adapter.AdapterEpisode
import com.example.rickandmorty.viewmodel.ModelFactory
import com.example.rickandmorty.viewmodel.VMEpisode
import java.util.ArrayList


class EpisodeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_episode, container, false)

        val vmEpisode : VMEpisode? = ModelFactory.create(VMEpisode::class.java)

        val recyclerView: RecyclerView? = view.findViewById(R.id.r_view_episode)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.setHasFixedSize(true)


        val episode : ArrayList<String>? = activity?.intent?.getStringArrayListExtra("episodes")

        val adapter = AdapterEpisode()
        vmEpisode?.getData()?.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list.filter { episode?.contains(it.url) == true }.toList())
        }
        recyclerView?.adapter = adapter

        return view
    }
}