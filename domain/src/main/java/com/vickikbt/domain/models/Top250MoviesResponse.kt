package com.vickikbt.domain.models


data class Top250MoviesResponse(
    val errorMessage: String?,

    val top250Movies: List<Top250Movie>?
)