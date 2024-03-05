package com.bakanito.bakanitoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.bakanito.bakanitoapp.model.PokemonModel
import com.bakanito.bakanitoapp.adapter.PokedexAdapter
import com.bakanito.bakanitoapp.api.APIService
import com.bakanito.bakanitoapp.databinding.ActivityPokedexBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokedexActivity : AppCompatActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityPokedexBinding
    private lateinit var adapter: PokedexAdapter
    private var pokemonList = mutableListOf<PokemonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPokedexBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svPokemonFilter.setOnQueryTextListener(this)

        initRecyclerView()

        getPokedex()
    }

    private fun initRecyclerView() {
        adapter = PokedexAdapter(pokemonList) { pokemon -> onItemSelected(pokemon) }
        binding.rvPokedex.layoutManager = GridLayoutManager(this, 3)
        binding.rvPokedex.adapter = adapter

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/ ")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getPokedex() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java)
                .getPokedex("v2/pokemon?offset=0&limit=151")
            val data = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val pokedex = data?.pokedexList ?: emptyList()
                    pokedex.forEach { pokemonURL ->
                        searchPokemon(pokemonURL.url)
                    }
                } else {
                    showError()
                }
            }
        }
    }

    private fun searchPokemon(query: String) {
        CoroutineScope(Dispatchers.Default).launch {
            val call = getRetrofit().create(APIService::class.java).getPokemon(query)
            val data = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val indexPokemon = data?.index?.toInt() ?: 0
                    val namePokemon = data?.name ?: ""
                    val backDefault = data?.sprites?.back_default ?: ""
                    val backShiny = data?.sprites?.back_shiny ?: ""
                    val frontDefault = data?.sprites?.front_default ?: ""
                    val frontShiny = data?.sprites?.front_shiny ?: ""
                    val pokemon = PokemonModel(
                        indexPokemon,
                        namePokemon,
                        frontDefault,
                        backDefault,
                        frontShiny,
                        backShiny
                    )
                    pokemonList.add(pokemon)
                    pokemonList.sortBy { it.index }
                    adapter.notifyDataSetChanged()
                }else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
    }

    private fun onItemSelected(pokemonModel: PokemonModel) {
        //info pokemon
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        var query = ""
        if (!newText.isNullOrEmpty()) {
            query = newText.lowercase()
        }
        val pokedexFiltered =
            pokemonList.filter { pokemon -> pokemon.name.contains(query) }
        adapter.updatePokedex(pokedexFiltered)

        return true
    }
}