package com.bakanito.bakanitoapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bakanito.bakanitoapp.model.PokemonModel
import com.bakanito.bakanitoapp.databinding.ItemPokemonBinding
import com.squareup.picasso.Picasso
import java.util.Locale

class PokedexViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemPokemonBinding.bind(view)

    fun render(pokemonModel: PokemonModel, OnClickListener: (PokemonModel) -> Unit) {
        binding.tvPokemonName.text = pokemonModel.name.replaceFirstChar { it.titlecase() }
        binding.tvPokemonIndex.text = pokemonModel.index.toString()
        //Glide.with(binding.ivPokemonPhoto.context).load(pokemonModel.frontImg).into(binding.ivPokemonPhoto)
        Picasso.get().load(pokemonModel.frontImg).into(binding.ivPokemonPhoto)

        itemView.setOnClickListener { OnClickListener(pokemonModel) }
    }
}