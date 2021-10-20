package com.vickikbt.domain.models


data class Top250MoviesShowsResponse(
    val errorMessage: String?,

    val movies: List<MovieShow>?
)