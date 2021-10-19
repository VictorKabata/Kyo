package com.vickikbt.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "In_Theaters_Movies_Table")
data class InTheatersMovieEntity(
    val contentRating: String?,

    val directors: String?,

    val fullTitle: String?,

    val genres: String?,

    @PrimaryKey(autoGenerate = false)
    val id: String?,

    val imDbRating: String?,

    val imDbRatingCount: String?,

    val image: String?,

    val metacriticRating: String?,

    val plot: String?,

    val releaseState: String?,

    val runtimeMins: String?,

    val runtimeStr: String?,

    val starList: List<StarEntity>?,

    val stars: String?,

    val title: String?,

    val year: String?
)
