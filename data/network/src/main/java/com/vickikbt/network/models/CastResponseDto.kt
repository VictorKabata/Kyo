package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class CastResponseDto(
    @SerializedName("actors")
    val actors: List<ActorDto>?,

    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("fullTitle")
    val fullTitle: String?,

    @SerializedName("imDbId")
    val imDbId: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("year")
    val year: String?
)