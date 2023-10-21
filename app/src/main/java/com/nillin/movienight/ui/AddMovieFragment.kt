package com.nillin.movienight.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nillin.movienight.databinding.FragmentAddMovieBinding
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class AddMovieFragment : Fragment() {

    @Inject
    lateinit var movieRepo: MovieRepo

    private val binding by lazy { FragmentAddMovieBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                movieRepo.insert(
                    Movie(
                        title = binding.etTitle.text.toString(),
                        synopsis = binding.etSynopsis.text.toString(),
                        cover = binding.etCoverLink.text.toString(),
                    )
                )
            }
            findNavController().popBackStack()
        }
    }


}