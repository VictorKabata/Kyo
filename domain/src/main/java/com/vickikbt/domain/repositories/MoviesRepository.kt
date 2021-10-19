package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.InTheatersComingSoonMovie
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow

interface MoviesRepository {

    suspend fun fetchInTheatersMovies(): List<InTheatersComingSoonMovie>

    suspend fun fetchPopularMovies(): List<PopularMovieShow>

    suspend fun fetchTop250Movies(): List<Top250MovieShow>

    //suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersMovie>)

    //suspend fun savePopularMovies(popularMovies: List<PopularMovie>)

    //suspend fun saveTop250Movies(top250Movies: List<Top250Movie>)

}