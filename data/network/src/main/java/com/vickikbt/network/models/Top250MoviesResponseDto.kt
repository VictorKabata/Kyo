package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class Top250MoviesResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("items")
    val top250Movies: List<Top250MovieDto>?
)