package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Top_250_Movies_Table")
data class Top250MovieShowEntity(
    val crew: String?,

    val fullTitle: String?,

    @PrimaryKey(autoGenerate = false)
    val id: String,

    val imDbRating: String?,

    val imDbRatingCount: String?,

    val image: String?,

    val rank: String?,

    val title: String?,

    val year: String?,

    val category: String?
)