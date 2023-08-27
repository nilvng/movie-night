package com.nillin.movienight.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nillin.movienight.R
import com.nillin.movienight.databinding.FragmentMainBinding
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.asState
import com.nillin.movienight.ui.AddMovieBottomsheetFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding get() = _binding!!
    private var _binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        GridLayoutManager(context, 2).apply {
            binding.rvMovies.layoutManager = this
            binding.rvMovies.adapter = MovieAdapter()
            binding.rvMovies.layoutParams
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.movieRepo.getAll()
                    .map { movieList -> movieList.map { movie -> movie.asState() } }
                    .collect {
                        (binding.rvMovies.adapter as MovieAdapter).update(it)
                    }
            }
        }

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addFragment)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}