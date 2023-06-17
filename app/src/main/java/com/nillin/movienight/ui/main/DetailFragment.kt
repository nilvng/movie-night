package com.nillin.movienight.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nillin.movienight.R
import com.nillin.movienight.databinding.FragmentDetailBinding
import com.nillin.movienight.databinding.FragmentMainBinding

class DetailFragment : Fragment() {
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
        viewModel.fetch()
        viewModel.movie.observe(viewLifecycleOwner) {
            binding.tvSynopsisDetail.text = it.synopsis
            binding.tvAuthor.text = it.creator
            binding.tvTitle.text = it.title
            binding.tvYear.text = it.year.toString()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}