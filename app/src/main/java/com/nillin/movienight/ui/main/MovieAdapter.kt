package com.nillin.movienight.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nillin.movienight.R
import com.nillin.movienight.state.MovieUI
import com.nillin.movienight.databinding.ItemSearchBinding
import com.nillin.movienight.ui.detail.DetailFragment

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val differItemCallback = object : DiffUtil.ItemCallback<MovieUI>() {
        override fun areItemsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUI, newItem: MovieUI): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differItemCallback)

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
        differ.submitList(movieUIS)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)
    }

    override fun getItemCount() = differ.currentList.count()


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