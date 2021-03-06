package com.vickikbt.repository.data_sources

import androidx.lifecycle.MutableLiveData
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import com.vickikbt.repository.mappers.toEntity
import com.vickikbt.repository.utils.Coroutines
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : MoviesRepository {

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

    override suspend fun fetchInTheatersMovies(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable =
            appDatabase.moviesDao().isMovieShowCacheAvailable(category = Constants.IN_THEATERS) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.IN_THEATERS)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchInTheaterMovies()
            _inTheatersMoviesEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.IN_THEATERS) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.IN_THEATERS)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    override suspend fun fetchPopularMovies(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.POPULAR_MOVIE) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_MOVIE)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchPopularMovies()
            _popularMoviesEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.POPULAR_MOVIE) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.POPULAR_MOVIE)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    override suspend fun fetchTop250Movies(): Flow<List<MovieShow>> {
        val isCacheResponseAvailable = appDatabase.moviesDao()
            .isMovieShowCacheAvailable(category = Constants.TOP_250_MOVIE) > 0

        return if (isCacheResponseAvailable) {
            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_MOVIE)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        } else {
            val networkResponse = apiService.fetchTop250Movies()
            _top250MoviesEntity.value =
                networkResponse.movieShows?.map { it.toEntity(category = Constants.TOP_250_MOVIE) }

            val cacheResponse =
                appDatabase.moviesDao().getMoviesShows(category = Constants.TOP_250_MOVIE)
            cacheResponse.map { it.map { moviesShowList -> moviesShowList.toDomain() } }
        }
    }

    private suspend fun saveMoviesShows(movieShow: List<MovieShowEntity>) =
        appDatabase.moviesDao().saveMoviesShows(movieShow)
}