package com.vickikbt.domain.models


data class PopularMoviesShowResponse(
    val errorMessage: String?,

    val popularMovie: List<MovieShow>?
)