package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.repositories.TvShowsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.Coroutines

class TvShowsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : TvShowsRepository, SafeApiRequest() {

    private val _comingSoon = MutableLiveData<List<MovieShowEntity>>()
    private val _popularTvShowsEntity = MutableLiveData<List<MovieShowEntity>>()
    private val _top250TvShowsEntity = MutableLiveData<List<MovieShowEntity>>()

    init {
        _comingSoon.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }

        _popularTvShowsEntity.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }

        _top250TvShowsEntity.observeForever {
            Coroutines.io { saveMoviesShows(it) }
        }
    }

    override suspend fun fetchComingSoon(): List<MovieShow> {
        val isCacheResponseAvailable =
            appDatabase.moviesDao().isMovieShowCacheAvailable(category = Constants.COMING_SOON) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.COMING_SOON)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchComingSoon() }
            _comingSoon.value =
                networkResponse.inTheatersComingSoonMovies?.map { it.toEntity(category = Constants.COMING_SOON) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.COMING_SOON)
                .map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularTvShows(): List<MovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.POPULAR_TV_SHOW) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_TV_SHOW)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchPopularTvShows() }
            _popularTvShowsEntity.value =
                networkResponse.popularMovieShow?.map { it.toEntity(category = Constants.POPULAR_TV_SHOW) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_TV_SHOW)
                .map { it.toDomain() }
        }
    }

    override suspend fun fetchTop250TvShows(): List<MovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.TOP_250_TV_SHOW) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_TV_SHOW)
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchTop250TvShows() }
            _top250TvShowsEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.TOP_250_TV_SHOW) }

            appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_TV_SHOW)
                .map { it.toDomain() }
        }
    }

    private suspend fun saveMoviesShows(moviesShows: List<MovieShowEntity>) =
        appDatabase.moviesDao().saveMoviesShows(moviesShows)
}