package com.bakanito.bakanitoapp.api

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("id") val index: String?,
    @SerializedName("name") val name : String?,
    @SerializedName("sprites") val sprites : PokemonSpritesResponse
)

