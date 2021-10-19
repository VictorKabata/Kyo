package com.vickikbt.cache.models


data class PopularMoviesResponseEntity(
    val errorMessage: String?,

    val popularMovie: List<PopularMovieEntity>?
)