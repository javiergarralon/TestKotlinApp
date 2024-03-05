package com.bakanito.bakanitoapp.api

import com.google.gson.annotations.SerializedName

data class PokedexResponse(
    @SerializedName("count") val pokedexCount: Int,
    @SerializedName("next") val nextUrl: String?,
    @SerializedName("previous") val previousUrl: String?,
    @SerializedName("results") val pokedexList: List<PokemonURLResponse>
)
