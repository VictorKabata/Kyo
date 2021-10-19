package com.vickikbt.domain.models


data class InTheatersResponse(
    val errorMessage: String?,

    val inTheatersMovies: List<InTheatersMovie>?
)