package com.vickikbt.cache.models


data class Top250MoviesShowsResponseEntity(
    val errorMessage: String?,

    val movies: List<MovieShowEntity>?
)