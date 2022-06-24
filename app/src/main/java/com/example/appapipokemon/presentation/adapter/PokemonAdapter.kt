package com.example.appapipokemon.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appapipokemon.api.Pokemon
import com.example.appapipokemon.databinding.LayoutItemPokemonBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemonList = arrayListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding =
            LayoutItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.onBind(pokemonList)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setList(list: List<Pokemon>) {
        if (list != null) {
            pokemonList.clear()
            pokemonList.addAll(list)
            notifyDataSetChanged()
        }
    }

    class PokemonViewHolder(val layout: LayoutItemPokemonBinding) :
        RecyclerView.ViewHolder(layout.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(pokemon: List<Pokemon>) {
            layout.apply {
                textViewName.text = pokemon[adapterPosition].name
            }
        }
    }
}


