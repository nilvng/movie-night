package com.nillin.movienight.local.ratings

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RatingDao {
    @Query("SELECT * FROM rating")
    fun getAll(): Flow<List<Rating>>

    @Delete
    fun delete(rating: Rating)

    @Update
    fun update(rating: Rating)

    @Query("SELECT * FROM rating WHERE movie_id IN (:movieId)")
    suspend fun getRatingByMovie(movieId: Int): Rating

    @Query("SELECT * FROM rating WHERE user_id IN (:userId)")
    fun getByUser(userId: String): Flow<List<Rating>>
}