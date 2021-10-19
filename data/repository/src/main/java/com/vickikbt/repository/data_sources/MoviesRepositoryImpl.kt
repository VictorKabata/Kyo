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

    private val _inTheatersMoviesEntity = MutableLiveData<List<InTheatersComingSoonEntity>>()
    private val _popularMoviesEntity = MutableLiveData<List<PopularMovieShowEntity>>()
    private val _top250MoviesEntity = MutableLiveData<List<Top250MovieShowEntity>>()

    init {
        _inTheatersMoviesEntity.observeForever {
            Coroutines.io { saveInTheatersMovies(it) }
        }

        _popularMoviesEntity.observeForever {
            Coroutines.io { savePopularMovies(it) }
        }

        _top250MoviesEntity.observeForever {
            Coroutines.io { saveTop250Movies(it) }
        }
    }

    override suspend fun fetchInTheatersMovies(): List<InTheatersComingSoon> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isInTheaterMoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getInTheatersMovies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchInTheaterMovies() }
            _inTheatersMoviesEntity.value =
                networkResponse.inTheatersComingSoonMovies?.map { it.toEntity(category = Constants.IN_THEATERS) }

            appDatabase.moviesDao().getInTheatersMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularMovies(): List<PopularMovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isPopularMoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getPopularMovies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchPopularMovies() }
            _popularMoviesEntity.value =
                networkResponse.popularMovieShow?.map { it.toEntity(category = Constants.POPULAR_MOVIE) }

            appDatabase.moviesDao().getPopularMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchTop250Movies(): List<Top250MovieShow> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isTop250MoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getTop250Movies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchTop250Movies() }
            _top250MoviesEntity.value =
                networkResponse.top250MovieShows?.map { it.toEntity(category = Constants.TOP_250_MOVIE) }

            appDatabase.moviesDao().getTop250Movies().map { it.toDomain() }
        }
    }

    private suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersComingSoonEntity>) =
        appDatabase.moviesDao().saveInTheatersMovies(inTheaterMovies)

    private suspend fun savePopularMovies(popularMovies: List<PopularMovieShowEntity>) =
        appDatabase.moviesDao().savePopularMovies(popularMovies)

    private suspend fun saveTop250Movies(top250Movies: List<Top250MovieShowEntity>) =
        appDatabase.moviesDao().saveTop250Movies(top250Movies)
}