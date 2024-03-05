package com.bakanito.bakanitoapp.api

import com.google.gson.annotations.SerializedName

data class PokemonURLResponse(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)