package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.Coroutines

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository, SafeApiRequest() {

    private val _inTheatersMoviesEntity = MutableLiveData<List<MovieShowEntity>>()
    private val _popularMoviesEntity = MutableLiveData<List<MovieShowEntity>>()
    private val _top250MoviesEntity = MutableLiveData<List<MovieShowEntity>>()

    init {
        _inTheatersMoviesEntity.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }

        _popularMoviesEntity.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }

        _top250MoviesEntity.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }
    }

    override suspend fun fetchInTheatersMovies(): List<MovieShow> {
        val isCacheResponseAvailable =
            appDatabase.moviesDao().isMovieShowCacheAvailable(category = Constants.IN_THEATERS) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getMoviesShows(category = Constants.IN_THEATERS)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchInTheaterMovies() }
            _inTheatersMoviesEntity.value =
                networkResponse.inTheatersComingSoonMovies?.map { it.toEntity(category = Constants.IN_THEATERS) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.IN_THEATERS).map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularMovies(): List<MovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.POPULAR_MOVIE) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_MOVIE)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchPopularMovies() }
            _popularMoviesEntity.value =
                networkResponse.popularMovieShow?.map { it.toEntity(category = Constants.POPULAR_MOVIE) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_MOVIE)
                .map { it.toDomain() }
        }
    }

    override suspend fun fetchTop250Movies(): List<MovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.TOP_250_MOVIE) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_MOVIE)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchTop250Movies() }
            _top250MoviesEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.TOP_250_MOVIE) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_MOVIE)
                .map { it.toDomain() }
        }
    }

    private suspend fun saveMoviesShows(movieShow: List<MovieShowEntity>) =
        appDatabase.moviesDao().saveMoviesShows(movieShow)
}