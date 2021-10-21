package com.vickikbt.domain.models


data class MoviesShowResponse(
    val errorMessage: String?,

    val popularMovie: List<MovieShow>?
)