package com.nillin.movienight.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nillin.movienight.R
import com.nillin.movienight.state.MovieUI
import com.nillin.movienight.databinding.ItemSearchBinding
import com.nillin.movienight.ui.detail.DetailFragment

class MovieAdapter(private val movieUIS: ArrayList<MovieUI>) :
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

    fun update(movieUIS: List<MovieUI>) {
        this.movieUIS.clear()
        this.movieUIS.addAll(movieUIS)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieUIS[position], position)
    }

    override fun getItemCount() = movieUIS.count()


    class MovieViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieUI: MovieUI, position: Int) {

            Glide.with(binding.root.context)
                .load(movieUI.cover)
                .centerCrop()
                .placeholder(R.color.teal_200)
                .into(binding.ivCover)

            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    R.id.action_mainFragment_to_detailFragment,
                    args = Bundle().apply {
                        putInt(DetailFragment.ARG_POSITION, movieUI.id)
                    })
            }

            binding.tvTitle.text = movieUI.title
        }
    }
}