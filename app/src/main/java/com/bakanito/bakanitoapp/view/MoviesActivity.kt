package com.bakanito.bakanitoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakanito.bakanitoapp.model.MovieModel
import com.bakanito.bakanitoapp.MoviesProvider
import com.bakanito.bakanitoapp.adapter.MovieAdapter
import com.bakanito.bakanitoapp.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    fun initRecyclerView() {
        //val manager = GridLayoutManager(this, 2)
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerMovie.layoutManager = manager
        binding.recyclerMovie.adapter =
            MovieAdapter(MoviesProvider.moviesList) { movie -> onItemSelected(movie) }
        binding.recyclerMovie.addItemDecoration(decoration)
    }

    fun onItemSelected(movieModel: MovieModel) {
        Toast.makeText(
            this,
            "${movieModel.title}\n${movieModel.director}\n${movieModel.year}\n${movieModel.rating}",
            Toast.LENGTH_SHORT
        ).show()
    }
}