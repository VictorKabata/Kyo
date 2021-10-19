package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class InTheatersComingSoonResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val inTheatersComingSoonMovies: List<InTheatersComingSoonMovieDto>?
)