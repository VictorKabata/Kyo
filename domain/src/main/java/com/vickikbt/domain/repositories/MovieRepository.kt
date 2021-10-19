package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.InTheatersResponse
import com.vickikbt.domain.models.PopularMoviesResponse
import com.vickikbt.domain.models.Top250MoviesResponse

interface MovieRepository {

    suspend fun fetchInTheatersMovies(): InTheatersResponse

    suspend fun fetchPopularMovies(): PopularMoviesResponse

    suspend fun fetchTop250Movies(): Top250MoviesResponse

    suspend fun saveInTheatersMovies()

    suspend fun savePopularMovies()

    suspend fun saveTop250Movies()

}