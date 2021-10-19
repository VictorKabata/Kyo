package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.InTheatersMovieEntity
import com.vickikbt.cache.models.PopularMovieEntity
import com.vickikbt.cache.models.Top250MovieEntity
import com.vickikbt.domain.models.InTheatersMovie
import com.vickikbt.domain.models.PopularMovie
import com.vickikbt.domain.models.Top250Movie
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

    private val _inTheatersMoviesEntity = MutableLiveData<List<InTheatersMovieEntity>>()
    private val _popularMoviesEntity = MutableLiveData<List<PopularMovieEntity>>()
    private val _top250MoviesEntity = MutableLiveData<List<Top250MovieEntity>>()

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

    override suspend fun fetchInTheatersMovies(): List<InTheatersMovie> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isInTheaterMoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getInTheatersMovies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchInTheaterMovies() }
            _inTheatersMoviesEntity.value = networkResponse.inTheatersMovies?.map { it.toEntity() }

            appDatabase.moviesDao().getInTheatersMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchPopularMovies(): List<PopularMovie> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isPopularMoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getPopularMovies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchPopularMovies() }
            _popularMoviesEntity.value = networkResponse.popularMovie?.map { it.toEntity() }

            appDatabase.moviesDao().getPopularMovies().map { it.toDomain() }
        }
    }

    override suspend fun fetchTop250Movies(): List<Top250Movie> {
        val isCacheResponseAvailable = appDatabase.moviesDao().isTop250MoviesCacheAvailable() > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse = appDatabase.moviesDao().getTop250Movies()
            cacheResponse.map { it.toDomain() }
        } else {
            val networkResponse = safeApiRequest { apiService.fetchTop250Movies() }
            _top250MoviesEntity.value = networkResponse.top250Movies?.map { it.toEntity() }

            appDatabase.moviesDao().getTop250Movies().map { it.toDomain() }
        }
    }

    private suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersMovieEntity>) =
        appDatabase.moviesDao().saveInTheatersMovies(inTheaterMovies)

    private suspend fun savePopularMovies(popularMovies: List<PopularMovieEntity>) =
        appDatabase.moviesDao().savePopularMovies(popularMovies)

    private suspend fun saveTop250Movies(top250Movies: List<Top250MovieEntity>) =
        appDatabase.moviesDao().saveTop250Movies(top250Movies)
}