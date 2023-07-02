package com.nillin.movienight.local.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nillin.movienight.local.movie.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun getAll(): Flow<List<Bookmark>>

    @Query("SELECT * FROM bookmark WHERE id IN (:bookmarkIds)")
    fun loadAllByIds(bookmarkIds: IntArray): List<Bookmark>

    @Insert
    fun insertAll(vararg bookmarks: Bookmark)

    @Delete
    fun delete(bookmark: Bookmark)

    @Query("SELECT * FROM bookmark JOIN movie ON bookmark.movie_id = movie.id WHERE bookmark.id IN (:bookmarkId)")
    suspend fun getMovieByBookmark(bookmarkId: Int): Movie

    @Query("SELECT * FROM bookmark WHERE user_id IN (:userId)")
    fun getByUser(userId: String): Flow<List<Bookmark>>
}
