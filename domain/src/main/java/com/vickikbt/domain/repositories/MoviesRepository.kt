package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.MovieShow

interface MoviesRepository {

    suspend fun fetchInTheatersMovies(): List<MovieShow>

    suspend fun fetchPopularMovies(): List<MovieShow>

    suspend fun fetchTop250Movies(): List<MovieShow>

    //suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersMovie>)

    //suspend fun savePopularMovies(popularMovies: List<PopularMovie>)

    //suspend fun saveTop250Movies(top250Movies: List<Top250Movie>)

}