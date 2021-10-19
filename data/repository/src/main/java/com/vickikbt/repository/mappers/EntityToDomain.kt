package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.InTheatersMovieEntity
import com.vickikbt.cache.models.InTheatersResponseEntity
import com.vickikbt.cache.models.PopularMovieEntity
import com.vickikbt.cache.models.Top250MovieEntity
import com.vickikbt.domain.models.InTheatersMovie
import com.vickikbt.domain.models.InTheatersResponse
import com.vickikbt.domain.models.PopularMovie
import com.vickikbt.domain.models.Top250Movie

internal fun InTheatersMovieEntity.toDomain(): InTheatersMovie {
    return InTheatersMovie(
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

internal fun InTheatersResponseEntity.toDomain(): InTheatersResponse {
    return InTheatersResponse(
        errorMessage = this.errorMessage,
        inTheatersMovies = this.inTheatersMovies?.map { it.toDomain() })
}

internal fun PopularMovieEntity.toDomain(): PopularMovie {
    return PopularMovie(
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

internal fun Top250MovieEntity.toDomain(): Top250Movie {
    return Top250Movie(
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