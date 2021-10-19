package com.vickikbt.cache.models

import androidx.room.Entity

@Entity(tableName = "Popular_Movies_Table")
data class PopularMovieEntity(
    val crew: String?,

    val fullTitle: String?,

    val id: String?,

    val imDbRating: String?,

    val imDbRatingCount: String?,

    val image: String?,

    val rank: String?,

    val rankUpDown: String?,

    val title: String?,

    val year: String?
)