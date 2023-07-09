package com.nillin.movienight.di

import com.nillin.movienight.local.movie.MovieDao
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.MovieRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideMovieRepo(movieDao: MovieDao): MovieRepo {
        return MovieRepoImpl(movieDao)
    }
}