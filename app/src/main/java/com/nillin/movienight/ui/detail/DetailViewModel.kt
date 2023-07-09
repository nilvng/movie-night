package com.nillin.movienight.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.asState
import com.nillin.movienight.state.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    val movieRepo: MovieRepo
) : ViewModel(){
    val movieUI: LiveData<MovieUI?> get() = _movieUI
    private val _movieUI: MutableLiveData<MovieUI> = MutableLiveData()

    fun fetch(index: Int) {
        viewModelScope.launch {
            _movieUI.value = movieRepo.getById(index).asLiveData(coroutineContext).value?.asState()

        }
    }
}
