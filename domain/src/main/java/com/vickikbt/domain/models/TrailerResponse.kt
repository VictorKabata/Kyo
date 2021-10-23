package com.vickikbt.domain.models


data class TrailerResponse(
    val errorMessage: String?,

    val fullTitle: String?,

    val imDbId: String?,

    val link: String?,

    val linkEmbed: String?,

    val thumbnailUrl: String?,

    val title: String?,

    val type: String?,

    val uploadDate: String?,

    val videoDescription: String?,

    val videoId: String?,

    val videoTitle: String?,

    val year: String?
)