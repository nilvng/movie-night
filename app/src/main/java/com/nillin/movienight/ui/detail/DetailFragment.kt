package com.nillin.movienight.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nillin.movienight.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    companion object {
        const val ARG_POSITION = "position"
    }
    private val binding: FragmentDetailBinding get() = _binding!!
    private var _binding: FragmentDetailBinding? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = DetailViewModel(requireActivity().application)
        viewModel.movie.observe(viewLifecycleOwner) {
            binding.tvSynopsisDetail.text = it.synopsis
            binding.tvAuthor.text = it.creator
            binding.tvTitle.text = it.title
            binding.tvYear.text = it.year.toString()
            Glide.with(binding.root.context)
                .load(it.cover)
                .fitCenter()
                .into(binding.ivCoverPhoto)
        }
        arguments?.getInt(ARG_POSITION)?.let {
            viewModel.fetch(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}