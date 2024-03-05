package com.bakanito.bakanitoapp.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bakanito.bakanitoapp.model.MovieModel
import com.bakanito.bakanitoapp.databinding.ItemMovieBinding
import com.bumptech.glide.Glide

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemMovieBinding.bind(view)

    fun render(movieModel: MovieModel, onClickListener: (MovieModel) -> Unit) {
        binding.tvMovieTitle.text = movieModel.title
        binding.tvMovieDirector.text = movieModel.director
        binding.tvMovieYear.text = movieModel.year
        binding.tvMovieRating.text = movieModel.rating
        Glide.with(binding.ivMoviePhoto.context).load(movieModel.photo).into(binding.ivMoviePhoto)

        itemView.setOnClickListener{onClickListener(movieModel)}

    }
}