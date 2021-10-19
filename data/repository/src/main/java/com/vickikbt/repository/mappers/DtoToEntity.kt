package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.InTheatersMovieEntity
import com.vickikbt.cache.models.InTheatersResponseEntity
import com.vickikbt.cache.models.PopularMovieEntity
import com.vickikbt.cache.models.Top250MovieEntity
import com.vickikbt.network.models.InTheatersMovieDto
import com.vickikbt.network.models.InTheatersResponseDto
import com.vickikbt.network.models.PopularMovieDto
import com.vickikbt.network.models.Top250MovieDto

internal fun InTheatersMovieDto.toEntity(): InTheatersMovieEntity {
    return InTheatersMovieEntity(
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
        year = this.year
    )
}

internal fun InTheatersResponseDto.toEntity(): InTheatersResponseEntity {
    return InTheatersResponseEntity(
        errorMessage = this.errorMessage,
        inTheatersMovies = this.inTheatersMovies?.map { it.toEntity() })
}

internal fun PopularMovieDto.toEntity(): PopularMovieEntity {
    return PopularMovieEntity(
        crew = this.crew,
        fullTitle = this.fullTitle,
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        rank = this.rank,
        rankUpDown = this.rankUpDown,
        title = this.title,
        year = this.year
    )
}

internal fun Top250MovieDto.toEntity(): Top250MovieEntity {
    return Top250MovieEntity(
        crew = this.crew,
        fullTitle = this.fullTitle,
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        rank = this.rank,
        title = this.title,
        year = this.year
    )
}