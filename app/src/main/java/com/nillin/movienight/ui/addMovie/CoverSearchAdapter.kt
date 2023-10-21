package com.nillin.movienight.ui.addMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nillin.movienight.databinding.CoverSearchItemBinding

class CoverSearchAdapter : RecyclerView.Adapter<CoverSearchAdapter.CoverSearchViewHolder>() {

    var items: List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value.distinct())

    private val differItemCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differItemCallback)

    class CoverSearchViewHolder(private val coverSearchItemBinding: CoverSearchItemBinding) :
        RecyclerView.ViewHolder(coverSearchItemBinding.root) {
        fun bind(cover: String, position: Int) {
            coverSearchItemBinding.ivCover.apply {
                Glide.with(context)
                    .load(cover)
                    .into(this)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoverSearchViewHolder {
        return CoverSearchViewHolder(
            CoverSearchItemBinding.inflate(
               LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = differ.currentList.count()

    override fun onBindViewHolder(holder: CoverSearchViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)
    }
}