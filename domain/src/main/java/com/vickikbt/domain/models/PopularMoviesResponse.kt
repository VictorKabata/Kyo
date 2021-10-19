package com.vickikbt.domain.models


data class PopularMoviesResponse(
    val errorMessage: String?,

    val popularMovie: List<PopularMovie>?
)