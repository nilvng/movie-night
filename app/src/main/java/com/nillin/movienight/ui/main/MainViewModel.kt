package com.nillin.movienight.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.state.Movie
import com.nillin.movienight.state.dummy_normalpeople
import com.nillin.movienight.state.dummy_silo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

val dummy_data = listOf(dummy_normalpeople, dummy_silo)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepo
): ViewModel() {
    val movies: LiveData<List<Movie>> = MutableLiveData(dummy_data)
    private val _movie = MutableLiveData<List<Movie>>()
}