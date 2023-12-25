package com.nillin.movienight.ui.addMovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.network.tmdb.TmdbApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMovieViewModel @Inject constructor(
    private val movieRepo: MovieRepo

) : ViewModel() {

    val uiState: StateFlow<AddMovieUI> get() = _uiState
    private val _uiState = MutableStateFlow(AddMovieUI())

    fun onSearchClicked(title: String) = viewModelScope.launch {
        val search = TmdbApi.retrofitService.searchMovies(title)
        val bestSuggestion = search.results.firstOrNull() ?: return@launch
        _uiState.value = _uiState.value.copy(
            title = title,
            synopsis = bestSuggestion.overview,
            cover = bestSuggestion.imgSrcPath ?: "",
        )
    }

    fun onAddClicked(title: String, synopsis: String, cover: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val ui = _uiState.updateAndGet { it.copy(title = title, synopsis = synopsis, cover = cover) }

            val movie = Movie(
                title = ui.title,
                synopsis = ui.synopsis,
                cover = ui.cover,
            )
            movieRepo.insert(movie)
        }

}

data class AddMovieUI(
    val title: String = "",
    val synopsis: String = "",
    val cover: String = "",
)