package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class InTheatersComingSoonDto(
    @SerializedName("contentRating")
    val contentRating: String?,

    @SerializedName("directors")
    val directors: String?,

    @SerializedName("fullTitle")
    val fullTitle: String?,

    @SerializedName("genres")
    val genres: String?,

    @SerializedName("id")
    val id: String,

    @SerializedName("imDbRating")
    val imDbRating: String?,

    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("metacriticRating")
    val metacriticRating: String?,

    @SerializedName("plot")
    val plot: String?,

    @SerializedName("releaseState")
    val releaseState: String?,

    @SerializedName("runtimeMins")
    val runtimeMins: String?,

    @SerializedName("runtimeStr")
    val runtimeStr: String?,

    @SerializedName("stars")
    val stars: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("year")
    val year: String?,

    val category: String?
)
