package com.vickikbt.cache.models


data class PopularMoviesShowsResponseEntity(
    val errorMessage: String?,

    val popularMovie: List<PopularMovieShowEntity>?
)