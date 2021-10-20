package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class Top250MoviesShowsResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val movieShows: List<MovieShowDto>?
)