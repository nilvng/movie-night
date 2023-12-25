package com.nillin.movienight.ui.addMovie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nillin.movienight.databinding.FragmentAddMovieBinding
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.network.tmdb.TMDB_BASE_IMG_URL
import com.nillin.movienight.network.tmdb.TmdbApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
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
        binding.rvCoverSearch.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCoverSearch.adapter = CoverSearchAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {

            if (!isValidInput()) return@setOnClickListener

            lifecycleScope.launch(Dispatchers.IO) {
                movieRepo.insert(
                    Movie(
                        title = binding.etTitle.text.toString(),
                        synopsis = binding.etSynopsis.text.toString(),
                        cover = TMDB_BASE_IMG_URL +  binding.etCoverLink.text.toString(),
                    )
                )
            }
            findNavController().popBackStack()
        }

        binding.btnSearchByTitle.setOnClickListener {
            lifecycleScope.launch {
                val search = TmdbApi.retrofitService.searchMovies(binding.etTitle.text.toString())
                val bestSuggestion = search.results.firstOrNull() ?: return@launch
                binding.etSynopsis.setText(bestSuggestion.overview)
                binding.etCoverLink.setText(bestSuggestion.imgSrcPath)
                binding.btnSearchCover.performClick()
            }
        }

        binding.btnSearchCover.setOnClickListener {
            binding.rvCoverSearch.visibility = View.VISIBLE
            val imgQuery = TMDB_BASE_IMG_URL + binding.etCoverLink.text.toString()
            val currentItems = (binding.rvCoverSearch.adapter as CoverSearchAdapter).items
            (binding.rvCoverSearch.adapter as CoverSearchAdapter).items = currentItems + imgQuery
        }

        binding.etCoverLink.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                binding.etCoverLink.error = null
            }
        }
        binding.etTitle.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                binding.etCoverLink.error = null
            }
        }
    }

    private fun isValidInput(): Boolean {
        var isValid = true
        if (binding.etTitle.text.toString().isEmpty()) {
            binding.etTitle.error = "Required"
            isValid = false
        }
        if (binding.etCoverLink.text.toString().isEmpty()) {
            binding.etCoverLink.error = "Required"
            isValid = false
        }
        return isValid
    }

    override fun onResume() {
        super.onResume()
        binding.etTitle.requestFocus()

    }


}