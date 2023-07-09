package com.nillin.movienight.local.movie

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MovieRepoImpl @Inject constructor(
    private val movieDao: MovieDao
): MovieRepo {
    override suspend fun getAll(): Flow<List<Movie>> = movieDao.getAll()


    override suspend fun getById(movieId: Int): Flow<Movie> =  movieDao.getById(movieId)
}