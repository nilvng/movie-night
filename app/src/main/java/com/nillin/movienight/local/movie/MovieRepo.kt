package com.nillin.movienight.local.movie

import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    suspend fun getAll(): Flow<List<Movie>>
    suspend fun getById(movieId: Int): Flow<Movie>
}