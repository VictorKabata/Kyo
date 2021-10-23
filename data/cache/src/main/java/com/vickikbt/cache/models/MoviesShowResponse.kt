package com.vickikbt.cache.models


data class MoviesShowResponse(
    val errorMessage: String?,

    val movies: List<MovieShowEntity>?
)