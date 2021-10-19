package com.vickikbt.domain.models


data class Top250MoviesShowsResponse(
    val errorMessage: String?,

    val top250Movies: List<Top250MovieShow>?
)