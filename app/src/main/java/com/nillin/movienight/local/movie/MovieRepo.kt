package com.nillin.movienight.local.movie

import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getAll(): Flow<List<Movie>>
    fun getById(movieId: Int): Flow<Movie?>
    suspend fun insert(movie: Movie)
}