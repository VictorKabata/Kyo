package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class InTheatersResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val inTheatersMovies: List<InTheatersMovieDto>?
)