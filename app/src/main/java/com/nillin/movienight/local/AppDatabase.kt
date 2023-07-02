package com.nillin.movienight.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nillin.movienight.local.bookmark.Bookmark
import com.nillin.movienight.local.bookmark.BookmarkDao
import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieDao
import com.nillin.movienight.local.ratings.Rating
import com.nillin.movienight.local.ratings.RatingDao
import com.nillin.movienight.local.user.User
import com.nillin.movienight.local.user.UserDao

@Database(entities = [Bookmark::class, Movie::class, Rating::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
    abstract fun movieDao(): MovieDao
    abstract fun ratingDao(): RatingDao
    abstract fun userDao(): UserDao
}