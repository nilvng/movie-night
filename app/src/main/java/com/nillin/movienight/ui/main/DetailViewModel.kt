package com.nillin.movienight.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nillin.movienight.R
import com.nillin.movienight.database.Movie
import com.nillin.movienight.database.dummy_normalpeople
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject constructor(
        application: Application
    )
    : BaseViewModel(application) {
    val movie : LiveData<Movie> get() = _movie
    private val _movie : MutableLiveData<Movie> = MutableLiveData()

    fun fetch() {
        _movie.value = dummy_normalpeople
    }
}

@HiltViewModel
open class BaseViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    protected val context
        get() = getApplication<Application>()
}