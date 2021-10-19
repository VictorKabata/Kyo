package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.*

interface MovieRepository {

    suspend fun fetchInTheatersMovies(): InTheatersResponse

    suspend fun fetchPopularMovies(): PopularMoviesResponse

    suspend fun fetchTop250Movies(): Top250MoviesResponse

    suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersMovie>)

    suspend fun savePopularMovies(popularMovies: List<PopularMovie>)

    suspend fun saveTop250Movies(top250Movies: List<Top250Movie>)

}