package com.nillin.movienight.local.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id IN (:movieId)")
    fun getById(movieId: Int): Flow<Movie?>

    @Insert
    fun insert(movie: Movie)

}