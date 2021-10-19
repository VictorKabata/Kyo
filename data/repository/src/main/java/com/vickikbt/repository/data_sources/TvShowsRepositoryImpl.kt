package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.InTheatersComingSoon
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow
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

    private val _comingSoon = MutableLiveData<List<InTheatersComingSoonEntity>>()
    private val _popularTvShowsEntity = MutableLiveData<List<PopularMovieShowEntity>>()
    private val _top250TvShowsEntity = MutableLiveData<List<Top250MovieShowEntity>>()

    init {
        _comingSoon.observeForever {
            Coroutines.io { saveComingSoon(it) }
        }

        _popularTvShowsEntity.observeForever {
            Coroutines.io { savePopularTvShows(it) }
        }

        _top250TvShowsEntity.observeForever {
            Coroutines.io { saveTop250TvShows(it) }
        }
    }

    override suspend fun fetchComingSoon(): List<InTheatersComingSoon> {
        val isCacheResponseAvailable = appDatabase.tvShowsDao().isComingSoonCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.tvShowsDao().getComingSoon()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchComingSoon() }
            _comingSoon.value =
                networkResponse.inTheatersComingSoonMovies?.map { it.toEntity(category = Constants.COMING_SOON) }

            appDatabase.moviesDao().getInTheatersMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularTvShows(): List<PopularMovieShow> {
        val isCacheResponseAvailable = appDatabase.tvShowsDao().isPopularTvShowCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.tvShowsDao().getPopularTvShows()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchPopularTvShows() }
            _popularTvShowsEntity.value =
                networkResponse.popularMovieShow?.map { it.toEntity(category = Constants.POPULAR_TV_SHOW) }

            appDatabase.moviesDao().getPopularMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchTop250TvShows(): List<Top250MovieShow> {
        val isCacheResponseAvailable = appDatabase.tvShowsDao().isTop250TvShowsCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.tvShowsDao().getTop250TvShows()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchTop250TvShows() }
            _top250TvShowsEntity.value =
                networkResponse.top250MovieShows?.map { it.toEntity(category = Constants.TOP_250_TV_SHOW) }

            appDatabase.moviesDao().getTop250Movies().map { it.toDomain() }
        }
    }

    private suspend fun saveComingSoon(inTheaterMovies: List<InTheatersComingSoonEntity>) =
        appDatabase.moviesDao().saveInTheatersMovies(inTheaterMovies)

    private suspend fun savePopularTvShows(popularMovies: List<PopularMovieShowEntity>) =
        appDatabase.moviesDao().savePopularMovies(popularMovies)

    private suspend fun saveTop250TvShows(top250Movies: List<Top250MovieShowEntity>) =
        appDatabase.moviesDao().saveTop250Movies(top250Movies)
}