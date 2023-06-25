package com.nillin.movienight.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nillin.movienight.R
import com.nillin.movienight.database.Movie
import com.nillin.movienight.databinding.ItemSearchBinding
import com.nillin.movienight.ui.detail.DetailFragment

class MovieAdapter(private val movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }



    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], position)
    }

    override fun getItemCount() = movies.count()


    class MovieViewHolder(private val binding: ItemSearchBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, position: Int) {

            Glide.with(binding.root.context)
                .load(movie.cover)
                .centerCrop()
                .placeholder(R.color.teal_200)
                .into(binding.ivCover)

            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    args = Bundle().apply {
                        putInt(DetailFragment.ARG_POSITION, position)
                    })
            }

            binding.tvTitle.text = movie.title
        }
    }
}