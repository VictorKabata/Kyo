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
import com.vickikbt.domain.repositories.TvShowsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.network.models.MovieShowDto
import com.vickikbt.network.models.MoviesShowsResponseDto
import com.vickikbt.repository.data_sources.TvShowsRepositoryImpl
import com.vickikbt.repository.mappers.toEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TvShowsRepositoryImplTest {

    //Test subject
    private lateinit var tvShowsRepository: TvShowsRepository

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
            onBlocking { apiService.fetchComingSoon() }.doReturn(moviesShowApiResponse)
        }

        apiService.stub {
            onBlocking { apiService.fetchPopularTvShows() }.doReturn(moviesShowApiResponse)
        }

        apiService.stub {
            onBlocking { apiService.fetchTop250TvShows() }.doReturn(moviesShowApiResponse)
        }

        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        moviesShowsDao = appDatabase.moviesDao()

        tvShowsRepository = TvShowsRepositoryImpl(apiService, appDatabase)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun fetchComingSoon_returnsComingSoon_InApi() = runBlocking {
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.COMING_SOON) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.COMING_SOON).first()
        } else {
            networkResult = apiService.fetchComingSoon().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.COMING_SOON) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.COMING_SOON).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.COMING_SOON) })
        }
        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchComingSoon_returnsComingSoon_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.COMING_SOON) })
        val isCacheAvailable = moviesShowsDao.isMovieShowCacheAvailable(Constants.COMING_SOON) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.COMING_SOON).first()
            assertThat(cacheResult).isNotEmpty()
        } else {
            networkResult = apiService.fetchComingSoon().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.COMING_SOON) })
            cacheResult = moviesShowsDao.getMoviesShows(Constants.COMING_SOON).first()
        }
        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.COMING_SOON) })
    }

    @Test
    fun fetchPopularTvShows_returnsPopularTvShows_InApi() = runBlocking {
        val isCacheAvailable =
            moviesShowsDao.isMovieShowCacheAvailable(Constants.POPULAR_TV_SHOW) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_TV_SHOW).first()
        } else {
            networkResult = apiService.fetchPopularTvShows().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.POPULAR_TV_SHOW) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_TV_SHOW).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.POPULAR_TV_SHOW) })
        }

        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchPopularTvShows_returnsPopularTvShows_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.POPULAR_TV_SHOW) })
        val isCacheAvailable =
            moviesShowsDao.isMovieShowCacheAvailable(Constants.POPULAR_TV_SHOW) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_TV_SHOW).first()
        } else {
            networkResult = apiService.fetchPopularTvShows().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.POPULAR_TV_SHOW) })
            cacheResult = moviesShowsDao.getMoviesShows(Constants.POPULAR_TV_SHOW).first()
        }

        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.POPULAR_TV_SHOW) })
    }

    @Test
    fun fetchTop250TvShows_returnsTop250TvShows_InApi() = runBlocking {
        val isCacheAvailable =
            moviesShowsDao.isMovieShowCacheAvailable(Constants.TOP_250_TV_SHOW) > 0

        assertThat(isCacheAvailable).isFalse()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_TV_SHOW).first()
        } else {
            networkResult = apiService.fetchTop250TvShows().movieShows!!
            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.TOP_250_TV_SHOW) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_TV_SHOW).first()
            assertThat(cacheResult).isEqualTo(networkResult.map { it.toEntity(Constants.TOP_250_TV_SHOW) })
        }

        assertThat(networkResult).isEqualTo(moviesShowsDtos)
    }

    @Test
    fun fetchTop250TvShows_returnsTop250TvShows_InCache() = runBlocking {
        moviesShowsDao.saveMoviesShows(moviesShowsDtos.map { it.toEntity(Constants.TOP_250_TV_SHOW) })
        val isCacheAvailable =
            moviesShowsDao.isMovieShowCacheAvailable(Constants.TOP_250_TV_SHOW) > 0

        assertThat(isCacheAvailable).isTrue()

        if (isCacheAvailable) {
            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_TV_SHOW).first()
        } else {
            networkResult = apiService.fetchTop250TvShows().movieShows!!
            assertThat(networkResult).isEmpty()

            moviesShowsDao.saveMoviesShows(networkResult.map { it.toEntity(Constants.TOP_250_TV_SHOW) })

            cacheResult = moviesShowsDao.getMoviesShows(Constants.TOP_250_TV_SHOW).first()
            assertThat(cacheResult).isEmpty()
        }

        assertThat(cacheResult).isEqualTo(moviesShowsDtos.map { it.toEntity(Constants.TOP_250_TV_SHOW) })
    }

}