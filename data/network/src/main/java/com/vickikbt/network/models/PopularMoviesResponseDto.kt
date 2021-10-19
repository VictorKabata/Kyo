package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class PopularMoviesResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val popularMovie: List<PopularMovieDto>?
)