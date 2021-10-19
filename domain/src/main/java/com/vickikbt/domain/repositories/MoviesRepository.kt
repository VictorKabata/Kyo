package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.InTheatersMovie
import com.vickikbt.domain.models.PopularMovie
import com.vickikbt.domain.models.Top250Movie

interface MoviesRepository {

    suspend fun fetchInTheatersMovies(): List<InTheatersMovie>

    suspend fun fetchPopularMovies(): List<PopularMovie>

    suspend fun fetchTop250Movies(): List<Top250Movie>

    //suspend fun saveInTheatersMovies(inTheaterMovies: List<InTheatersMovie>)

    //suspend fun savePopularMovies(popularMovies: List<PopularMovie>)

    //suspend fun saveTop250Movies(top250Movies: List<Top250Movie>)

}