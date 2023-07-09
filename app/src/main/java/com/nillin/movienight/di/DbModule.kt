package com.nillin.movienight.di

import android.content.Context
import androidx.room.Room
import com.nillin.movienight.local.AppDatabase
import com.nillin.movienight.local.movie.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    fun provide(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "movie-night-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()
}