package com.vickikbt.cache.models


data class InTheatersComingSoonResponseEntity(
    val errorMessage: String?,

    val inTheatersMovies: List<InTheatersComingSoonEntity>?
)