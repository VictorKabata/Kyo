package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.MovieShow
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun fetchInTheatersMovies(): Flow<List<MovieShow>>

    suspend fun fetchPopularMovies(): Flow<List<MovieShow>>

    suspend fun fetchTop250Movies(): Flow<List<MovieShow>>

    //suspend fun saveMoviesShows(movieShow: List<MovieShow>)

}