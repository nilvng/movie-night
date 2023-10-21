package com.nillin.movienight.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.asUi
import com.nillin.movienight.state.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {
    val flowMovieUI
        get() = movieRepo.getAll().map { movieList ->
            movieList.map { it.asUi() }
        }.shareIn(viewModelScope, started = SharingStarted.WhileSubscribed())

}