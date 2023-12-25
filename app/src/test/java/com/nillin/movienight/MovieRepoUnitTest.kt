package com.nillin.movienight

import com.nillin.movienight.local.movie.Movie
import com.nillin.movienight.local.movie.MovieDao
import com.nillin.movienight.local.movie.MovieRepo
import com.nillin.movienight.local.movie.MovieRepoImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MovieRepoUnitTest {
    val repo: MovieRepo = MovieRepoImpl(MockMovieDao())

    @Test
    fun addMovie_returnCorrectList() = runTest {

        // arrange
        val movie = Movie(1, "Test Movie", "Test Description", "Test Poster")
        val previousList = repo.getAll().first()
        assertFalse(previousList.contains(movie))

        // action
        launch { repo.insert(movie) }

        // assert
        repo.getAll().onEach { updatedList ->
            assertTrue(updatedList.contains(movie))

        }.launchIn(this)
    }
}

private class MockMovieDao : MovieDao {

    val list = mutableListOf<Movie>()
    override fun getAll(): Flow<List<Movie>> {
        return flow { emit(list) }
    }

    override fun getById(movieId: Int): Flow<Movie?> {
        return flow { emit(list.find { it.id == movieId }) }
    }

    override fun insert(movie: Movie) {
        list.add(movie)
    }

}