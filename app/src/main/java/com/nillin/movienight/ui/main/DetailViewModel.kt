package com.nillin.movienight.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nillin.movienight.common.ContextViewModel
import com.nillin.movienight.database.Movie
import com.nillin.movienight.database.dummy_normalpeople
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    application: Application
) : ContextViewModel(application) {
    val movie: LiveData<Movie> get() = _movie
    private val _movie: MutableLiveData<Movie> = MutableLiveData()

    fun fetch() {
        _movie.value = dummy_normalpeople
    }
}
