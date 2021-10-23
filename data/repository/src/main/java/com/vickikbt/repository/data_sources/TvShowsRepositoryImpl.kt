package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.repositories.TvShowsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TvShowsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : TvShowsRepository {

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

    override suspend fun fetchComingSoon(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable =
            appDatabase.moviesDao().isMovieShowCacheAvailable(category = Constants.COMING_SOON) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.COMING_SOON)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchComingSoon()
            _comingSoon.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.COMING_SOON) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.COMING_SOON)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    override suspend fun fetchPopularTvShows(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.POPULAR_TV_SHOW) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_TV_SHOW)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchPopularTvShows()
            _popularTvShowsEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.POPULAR_TV_SHOW) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_TV_SHOW)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    override suspend fun fetchTop250TvShows(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.TOP_250_TV_SHOW) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_TV_SHOW)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchTop250TvShows()
            _top250TvShowsEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.TOP_250_TV_SHOW) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_TV_SHOW)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    private suspend fun saveMoviesShows(moviesShows: List<MovieShowEntity>) =
        appDatabase.moviesDao().saveMoviesShows(moviesShows)
}