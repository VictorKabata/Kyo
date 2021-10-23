package com.vickikbt.network.models


import com.google.gson.annotations.SerializedName

data class ActorDto(
    @SerializedName("asCharacter")
    val asCharacter: String?,

    @SerializedName("id")
    val id: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("name")
    val name: String?
)