package com.example.rickandmorty.model.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.EpisodeActivity
import retrofit2.Callback
import java.util.ArrayList
import com.example.rickandmorty.data.character.Result as Character



//class CharacterDiffCallback: DiffUtil.ItemCallback<Character>() {
//    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
//        return oldItem == newItem
//    }
//
//}



//class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    val img_char: ImageView? = itemView.findViewById(R.id.image_character)
//    val txt_char_name: TextView? = itemView.findViewById(R.id.name_character)
//    val txt_char_gender: TextView? = itemView.findViewById(R.id.gender_character)
//
//    fun bind(character: Character) {
//        img_char?.let {
//            Glide.with(itemView.context)
//                .load(character.image)
//                .into(it)
//        }
//
//        itemView.setOnClickListener {
//            val intent = Intent(itemView.context, EpisodeActivity::class.java)
//            intent.putStringArrayListExtra("episodes", character.episode as ArrayList<String>)
//            if (itemView.context is Activity){
//                (itemView.context as Activity).startActivity(intent)
//            }
//        }
//
//        txt_char_name?.text = character.name
//        txt_char_gender?.text = character.gender
//    }
//
//}

//class AdapterCharacter : ListAdapter<Character, CharacterViewHolder>(CharacterDiffCallback()){

//    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
//        holder.bind(getItem(position))
//
//    }
//
//}

sealed class DataModel {
    data class CharacterModel(
        val image : String,
        val name : String,
        val gender : String,
        val episode: List<String>
    ) : DataModel()

    data class LoadButtonModel(
        val callback: () -> Unit
    ) : DataModel()
}



class AdapterCharacter : RecyclerView.Adapter<AdapterCharacter.DataAdapterViewHolder>(){

    private val adapterData = mutableListOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapterViewHolder {
        val layout = when (viewType) {
            TYPE_CHARACTER -> R.layout.character_item
            TYPE_BUTTON -> R.layout.button_item
            else -> throw IllegalArgumentException("Invalid type")
        }

        return DataAdapterViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.CharacterModel -> TYPE_CHARACTER
            is DataModel.LoadButtonModel -> TYPE_BUTTON
        }
    }

    override fun getItemCount(): Int = adapterData.size

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    override fun onBindViewHolder(holder: DataAdapterViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    companion object {
        private const val TYPE_CHARACTER = 0
        private const val TYPE_BUTTON = 1
    }

    class DataAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private fun bindCharacter(item: DataModel.CharacterModel){
            val img_char: ImageView? = itemView.findViewById(R.id.image_character)
            val txt_char_name: TextView? = itemView.findViewById(R.id.name_character)
            val txt_char_gender: TextView? = itemView.findViewById(R.id.gender_character)

            img_char?.let {
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(it)
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, EpisodeActivity::class.java)
                intent.putStringArrayListExtra("episodes", item.episode as ArrayList<String>)
                if (itemView.context is Activity){
                    (itemView.context as Activity).startActivity(intent)
                }
            }

            txt_char_name?.text = item.name
            txt_char_gender?.text = item.gender

        }

        private fun bindButton(item: DataModel.LoadButtonModel){
            val load_button : Button? = itemView.findViewById(R.id.load_button)
            load_button?.setOnClickListener {
                item.callback()
            }

        }

        fun bind(dataModel: DataModel){
            when (dataModel) {
                is DataModel.CharacterModel -> bindCharacter(dataModel)
                is DataModel.LoadButtonModel -> bindButton(dataModel)
            }
        }

    }

}