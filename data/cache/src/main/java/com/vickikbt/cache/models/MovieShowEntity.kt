package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies_Shows_Table")
data class MovieShowEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val imDbRating: String?,

    val imDbRatingCount: String?,

    val image: String?,

    val title: String?,

    val category: String?
)