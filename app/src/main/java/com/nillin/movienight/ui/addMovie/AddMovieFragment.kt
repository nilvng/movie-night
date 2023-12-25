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
import androidx.fragment.app.viewModels
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class AddMovieFragment : Fragment() {
    private val viewModel: AddMovieViewModel by viewModels()

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
            onBtnAddClicked()
        }

        binding.btnSearchByTitle.setOnClickListener {
            viewModel.onSearchClicked(binding.etTitle.text.toString())
        }

        observeViewModel()

        binding.btnSearchCover.setOnClickListener {
            triggerSearchCover()
        }

        binding.etCoverLink.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                binding.etCoverLink.error = null
            }
        }
        binding.etTitle.doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true) {
                binding.etTitle.error = null
            }
        }
    }

    private fun onBtnAddClicked() {
        if (!isValidInput()) return
        viewModel.onAddClicked(
            title = binding.etTitle.text.toString(),
            synopsis = binding.etSynopsis.text.toString(),
            cover = binding.etCoverLink.text.toString(),
        )
        findNavController().popBackStack()
    }

    private fun observeViewModel() {
        viewModel.uiState.onEach {
            binding.etTitle.setText(it.title)
            binding.etSynopsis.setText(it.synopsis)
            binding.etCoverLink.setText(it.cover)
            if (it.cover.isNotEmpty()) {
                triggerSearchCover()
            }
        }.launchIn(lifecycleScope)
    }

    private fun triggerSearchCover() {
        binding.rvCoverSearch.visibility = View.VISIBLE
        val imgQuery = TMDB_BASE_IMG_URL + binding.etCoverLink.text.toString()
        val currentItems = (binding.rvCoverSearch.adapter as CoverSearchAdapter).items
        (binding.rvCoverSearch.adapter as CoverSearchAdapter).items = currentItems + imgQuery
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