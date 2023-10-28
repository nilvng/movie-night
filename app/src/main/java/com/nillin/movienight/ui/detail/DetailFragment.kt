package com.nillin.movienight.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.nillin.movienight.databinding.FragmentDetailBinding
import com.nillin.movienight.local.movie.asUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        const val ARG_POSITION = "position"
    }

    private val binding: FragmentDetailBinding get() = _binding!!
    private var _binding: FragmentDetailBinding? = null
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARG_POSITION)?.let { pos ->
            viewModel.fetch(pos).flowWithLifecycle(lifecycle).filterNotNull()
                .map { it.asUi() }
                .onEach {
                    binding.tvSynopsisDetail.text = it.synopsis
                    binding.tvAuthor.text = it.creator
                    binding.tvTitle.text = it.title
                    binding.tvYear.text = it.year.toString()
                    Glide.with(binding.root.context)
                        .load(it.cover)
                        .fitCenter()
                        .into(binding.ivCoverPhoto)
                }.launchIn(lifecycleScope)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}