package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class StarDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)