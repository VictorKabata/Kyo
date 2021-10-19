package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class PopularMoviesShowsResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val popularMovieShow: List<PopularMovieShowDto>?
)