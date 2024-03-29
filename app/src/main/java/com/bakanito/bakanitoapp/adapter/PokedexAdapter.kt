package com.bakanito.bakanitoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakanito.bakanitoapp.model.PokemonModel
import com.bakanito.bakanitoapp.R

class PokedexAdapter(
    private var pokedexList: List<PokemonModel>,
    private val onClickListener: (PokemonModel) -> Unit
) : RecyclerView.Adapter<PokedexViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        return PokedexViewHolder(layoutInflate.inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val item = pokedexList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = pokedexList.size

    fun updatePokedex(pokedexList: List<PokemonModel>){
        this.pokedexList = pokedexList
        notifyDataSetChanged()
    }
}