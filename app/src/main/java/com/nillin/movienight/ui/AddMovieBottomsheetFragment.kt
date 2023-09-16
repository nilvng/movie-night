package com.nillin.movienight.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nillin.movienight.R
import com.nillin.movienight.databinding.FragmentAddMovieBottomsheetBinding
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieRepo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddMovieBottomsheetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class AddMovieBottomsheetFragment : Fragment() {

    @Inject
    lateinit var movieRepo: MovieRepo

    private val binding by lazy { FragmentAddMovieBottomsheetBinding.inflate(layoutInflater) }

    private val link: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                movieRepo.insert(
                    Movie(
                        id = UUID.randomUUID().timestamp().toInt(),
                        title = binding.etTitle.text.toString(),
                        synopsis = binding.etSynopsis.text.toString(),
                        cover = link,
                    )
                )
            }
        }
    }


}