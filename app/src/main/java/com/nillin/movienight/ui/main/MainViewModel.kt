package com.nillin.movienight.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.asState
import com.nillin.movienight.state.MovieUI
import com.nillin.movienight.state.dummy_normalpeople
import com.nillin.movienight.state.dummy_silo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

val dummy_data = listOf(dummy_normalpeople, dummy_silo)

@HiltViewModel
class MainViewModel @Inject constructor(
    val movieRepo: MovieRepo
): ViewModel() {
    var movies: LiveData<List<MovieUI>> = MutableLiveData(dummy_data)
    var flowMovieUI : StateFlow<List<MovieUI>> = MutableStateFlow(dummy_data)
    private val _flowMovie = MutableStateFlow(dummy_data)

    fun getMovies() {
        viewModelScope.launch {
            flowMovieUI = movieRepo.getAll().map { it.map { it.asState() }}.stateIn(viewModelScope)
        }
    }
}