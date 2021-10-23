package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class TrailerResponseDto(
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("fullTitle")
    val fullTitle: String?,
    @SerializedName("imDbId")
    val imDbId: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("linkEmbed")
    val linkEmbed: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uploadDate")
    val uploadDate: String?,
    @SerializedName("videoDescription")
    val videoDescription: String?,
    @SerializedName("videoId")
    val videoId: String?,
    @SerializedName("videoTitle")
    val videoTitle: String?,
    @SerializedName("year")
    val year: String?
)