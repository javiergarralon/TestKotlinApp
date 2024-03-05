package com.bakanito.bakanitoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bakanito.bakanitoapp.model.MovieModel
import com.bakanito.bakanitoapp.R

class MovieAdapter(
    private val moviesList: List<MovieModel>,
    private val onClickListener: (MovieModel) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflate = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflate.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = moviesList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = moviesList.size
}