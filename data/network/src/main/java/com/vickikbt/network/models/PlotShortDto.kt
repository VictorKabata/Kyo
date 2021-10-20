package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class PlotShortDto(
    @SerializedName("html")
    val html: String?,

    @SerializedName("plainText")
    val plainText: String?
)