package com.vickikbt.cache.models


data class Top250MoviesResponseEntity(
    val errorMessage: String?,

    val top250Movies: List<Top250MovieEntity>?
)