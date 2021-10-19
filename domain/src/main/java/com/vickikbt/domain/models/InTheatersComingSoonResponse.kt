package com.vickikbt.domain.models


data class InTheatersComingSoonResponse(
    val errorMessage: String?,

    val inTheatersMovies: List<InTheatersComingSoon>?
)