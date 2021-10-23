package com.vickikbt.domain.models


data class PlotResponse(
    val errorMessage: String?,

    val fullTitle: String?,

    val imDbId: String?,

    val language: String?,

    val plotShort: PlotShort?,

    val title: String?,

    val titleInLanguage: String?,

    val type: String?,

    val url: String?,

    val year: String?
)