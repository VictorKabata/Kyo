package com.vickikbt.cache.models


data class InTheatersResponseEntity(
    val errorMessage: String?,

    val inTheatersMovies: List<InTheatersMovieEntity>?
)