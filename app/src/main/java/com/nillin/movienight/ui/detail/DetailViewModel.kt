package com.nillin.movienight.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.asUi
import com.nillin.movienight.state.MovieUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    val movieRepo: MovieRepo
) : ViewModel(){

    fun fetch(index: Int) = movieRepo.getById(index)

}
