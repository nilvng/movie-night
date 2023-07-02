package com.nillin.movienight.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nillin.movienight.state.Movie
import com.nillin.movienight.state.dummy_normalpeople
import com.nillin.movienight.state.dummy_silo

val dummy_data = listOf(dummy_normalpeople, dummy_silo)

class MainViewModel : ViewModel() {
    val movies: LiveData<List<Movie>> = MutableLiveData(dummy_data)
}