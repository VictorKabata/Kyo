package com.vickikbt.data_sources

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.daos.MoviesShowsDao
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.network.ApiService
import com.vickikbt.network.models.MovieShowDto
import com.vickikbt.network.models.MoviesShowsResponseDto
import com.vickikbt.repository.data_sources.MoviesRepositoryImpl
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieRepositoryImplTest {

    //Test subject
    private lateinit var moviesRepository: MoviesRepository

    //Collaborators
    private lateinit var apiService: ApiService
    private lateinit var appDatabase: AppDatabase
    private lateinit var moviesShowsDao: MoviesShowsDao

    //Utilities
    private var movieShowDto = MovieShowDto("id", "6.9", "420", "image", "title", null)
    private val moviesShowsDtos = listOf(movieShowDto)
    private val moviesShowApiResponse = MoviesShowsResponseDto("error", moviesShowsDtos)

    private lateinit var cacheResult: List<MovieShowEntity>
    private lateinit var networkResult: List<MovieShowDto>

    @Before
    fun setUp() {
        apiService = mock()

        apiService.stub {
            onBlocking { apiService.fetchInTheaterMovies() }.doReturn(moviesShowApiResponse)
        }

        apiService.stub {
            onBlocking { apiService.fetchPopularMovies() }.doReturn(moviesShowApiResponse)
        }

        apiService.stub {
            onBlocking { apiService.fetchTop250Movies() }.doReturn(moviesShowApiResponse)
        }

        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        moviesShowsDao = appDatabase.moviesDao()

        moviesRepository = MoviesRepositoryImpl(apiService, appDatabase)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun fetchTheaterMovies_returnsTheaterMovie_InApi() = runBlocking {
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.IN_THEATERS) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.IN_THEATERS).first()
        } else {
            networkResult = apiService.fetchInTheaterMovies().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.IN_THEATERS) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.IN_THEATERS).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.IN_THEATERS) })
        }
        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchTheaterMovies_returnsTheaterMovie_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.IN_THEATERS) })
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.IN_THEATERS) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.IN_THEATERS).first()
            assertThat(cacheResult).isNotEmpty()
        } else {
            networkResult = apiService.fetchInTheaterMovies().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.IN_THEATERS) })
            cacheResult = moviesShowsDao.getMoviesShows(Constants.IN_THEATERS).first()
        }
        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.IN_THEATERS) })
    }

    @Test
    fun fetchPopularMovies_returnsPopularMovie_InApi() = runBlocking {
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.POPULAR_MOVIE) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_MOVIE).first()
        } else {
            networkResult = apiService.fetchPopularMovies().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.POPULAR_MOVIE) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_MOVIE).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.POPULAR_MOVIE) })
        }

        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchPopularMovies_returnsPopularMovie_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.POPULAR_MOVIE) })
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.POPULAR_MOVIE) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_MOVIE).first()
        } else {
            networkResult = apiService.fetchPopularMovies().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.POPULAR_MOVIE) })
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_MOVIE).first()
        }

        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.POPULAR_MOVIE) })
    }

    @Test
    fun fetchTop250Movies_returnsTop250Movie_InApi() = runBlocking {
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.TOP_250_MOVIE) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_MOVIE).first()
        } else {
            networkResult = apiService.fetchTop250Movies().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.TOP_250_MOVIE) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_MOVIE).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.TOP_250_MOVIE) })
        }

        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchTop250Movies_returnsTop250Movie_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.TOP_250_MOVIE) })
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.TOP_250_MOVIE) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_MOVIE).first()
        } else {
            networkResult = apiService.fetchInTheaterMovies().movieShows!!
            assertThat(networkResult).isEmpty()

            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.TOP_250_MOVIE) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_MOVIE).first()
            assertThat(cacheResult).isEmpty()
        }

        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.TOP_250_MOVIE) })
    }

}