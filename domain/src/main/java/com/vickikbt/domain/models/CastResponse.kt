package com.vickikbt.domain.models


data class CastResponse(
    val actors: List<Actor>?,

    val errorMessage: String?,

    val fullTitle: String?,

    val imDbId: String?,

    val title: String?,

    val type: String?,

    val year: String?
)