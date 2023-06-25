package com.nillin.movienight.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nillin.movienight.database.Movie
import com.nillin.movienight.database.dummy_normalpeople
import com.nillin.movienight.database.dummy_silo

class MainViewModel : ViewModel() {
    val movies: LiveData<List<Movie>> = MutableLiveData(listOf(dummy_normalpeople, dummy_silo))
}