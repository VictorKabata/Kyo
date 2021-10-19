package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity
import com.vickikbt.network.models.InTheatersComingSoonDto
import com.vickikbt.network.models.PopularMovieShowDto
import com.vickikbt.network.models.Top250MovieShowDto

internal fun InTheatersComingSoonDto.toEntity(category: String? = null): InTheatersComingSoonEntity {
    return InTheatersComingSoonEntity(
        contentRating = this.contentRating,
        directors = this.directors,
        fullTitle = this.fullTitle,
        genres = this.genres,
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        metacriticRating = this.metacriticRating,
        plot = this.plot,
        releaseState = this.releaseState,
        runtimeMins = this.runtimeMins,
        runtimeStr = this.runtimeStr,
        stars = this.stars,
        title = this.title,
        year = this.year,
        category = category
    )
}

internal fun PopularMovieShowDto.toEntity(category: String?): PopularMovieShowEntity {
    return PopularMovieShowEntity(
        crew = this.crew,
        fullTitle = this.fullTitle,
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        rank = this.rank,
        rankUpDown = this.rankUpDown,
        title = this.title,
        year = this.year,
        category = category
    )
}

internal fun Top250MovieShowDto.toEntity(category: String?): Top250MovieShowEntity {
    return Top250MovieShowEntity(
        crew = this.crew,
        fullTitle = this.fullTitle,
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        rank = this.rank,
        title = this.title,
        year = this.year,
        category = category
    )
}