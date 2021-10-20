package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class PlotResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,

    @SerializedName("fullTitle")
    val fullTitle: String?,

    @SerializedName("imDbId")
    val imDbId: String?,

    @SerializedName("language")
    val language: String?,

    @SerializedName("plotShort")
    val plotShort: PlotShortDto?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("titleInLanguage")
    val titleInLanguage: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("year")
    val year: String?
)