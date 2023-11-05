package com.nillin.movienight.ui.addMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nillin.movienight.databinding.FragmentAddMovieBinding
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.network.MarksApi
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

        lifecycleScope.launch {
            val mars = MarksApi.retrofitService.getRealEstate()
            Timber.d("mars.size: ${mars.take(5).map { it.imgSrcUrl }}")
        }
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

        binding.btnSearch.setOnClickListener {
            binding.rvCoverSearch.visibility = View.VISIBLE
            val currentItems = (binding.rvCoverSearch.adapter as CoverSearchAdapter).items
            (binding.rvCoverSearch.adapter as CoverSearchAdapter).items = currentItems + binding.etCoverLink.text.toString()
        }
    }


}