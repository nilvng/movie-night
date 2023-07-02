package com.nillin.movienight.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nillin.movienight.common.ContextViewModel
import com.nillin.movienight.state.Movie
import com.nillin.movienight.ui.main.dummy_data
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    application: Application
) : ContextViewModel(application) {
    val movie: LiveData<Movie> get() = _movie
    private val _movie: MutableLiveData<Movie> = MutableLiveData()

    fun fetch(index: Int) {
        _movie.value = dummy_data[index]
    }
}
