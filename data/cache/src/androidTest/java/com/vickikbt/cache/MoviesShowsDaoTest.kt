package com.vickikbt.cache

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.vickikbt.cache.daos.MoviesShowsDao
import com.vickikbt.cache.models.MovieShowEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesShowsDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var moviesShowsDao: MoviesShowsDao

    private var movieShowEntity = MovieShowEntity("id", "6.9", "420", "image", "title", "category")
    private val moviesShowsEntities = mutableListOf(movieShowEntity)

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        moviesShowsDao = appDatabase.moviesDao()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun testGetMoviesShows() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsEntities)

        val result = moviesShowsDao.getMoviesShows("category").first()

        assertThat(result).isEqualTo(moviesShowsEntities)
    }

    @Test
    fun testGetMoviesShowsById() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsEntities)

        val result = moviesShowsDao.getMoviesShowsById("id").first()

        assertThat(result).isEqualTo(moviesShowsEntities[0])
    }

    @Test
    fun testSaveMoviesShows() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsEntities)

        val result = moviesShowsDao.getMoviesShows("category").first()

        assertThat(result).isEqualTo(moviesShowsEntities)
    }

    @Test
    fun testDeleteMovieShows() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsEntities)

        val result = moviesShowsDao.getMoviesShows("category").first()
        assertThat(result).isEqualTo(moviesShowsEntities)

        moviesShowsDao.deleteMovieShows("category")
        val delResult = moviesShowsDao.getMoviesShows("category").first()
        assertThat(delResult).isEmpty()
    }

    @Test
    fun testIsMovieShowCacheAvailable() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsEntities)

        val result = moviesShowsDao.isMovieShowCacheAvailable("category")
        assertThat(result).isEqualTo(1)

        moviesShowsDao.deleteMovieShows("category")
        val delResult = moviesShowsDao.isMovieShowCacheAvailable("category")
        assertThat(delResult).isEqualTo(0)
    }
}