package com.bakanito.bakanitoapp.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getDogsByBreeds(@Url url: String):Response<DogsResponse>

    @GET
    suspend fun getPokedex(@Url url:String):Response<PokedexResponse>

    @GET
    suspend fun getPokemon(@Url url:String):Response<PokemonResponse>
}