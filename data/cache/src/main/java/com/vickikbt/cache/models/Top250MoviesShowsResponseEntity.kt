package com.vickikbt.cache.models


data class Top250MoviesShowsResponseEntity(
    val errorMessage: String?,

    val top250Movies: List<Top250MovieShowEntity>?
)