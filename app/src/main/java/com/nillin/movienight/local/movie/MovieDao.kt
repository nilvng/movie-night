package com.nillin.movienight.local.movie

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE id IN (:movieId)")
    suspend fun getById(movieId: Int): Movie

    @Query("SELECT * FROM movie WHERE genre IN (:genre)")
    suspend fun getByGenre(genre: String): List<Movie>
}