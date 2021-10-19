package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.InTheatersComingSoonResponseEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity
import com.vickikbt.domain.models.InTheatersComingSoonMovie
import com.vickikbt.domain.models.InTheatersComingSoonResponse
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow

internal fun InTheatersComingSoonEntity.toDomain(): InTheatersComingSoonMovie {
    return InTheatersComingSoonMovie(
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

internal fun InTheatersComingSoonResponseEntity.toDomain(): InTheatersComingSoonResponse {
    return InTheatersComingSoonResponse(
        errorMessage = this.errorMessage,
        inTheatersMovies = this.inTheatersMovies?.map { it.toDomain() })
}

internal fun PopularMovieShowEntity.toDomain(): PopularMovieShow {
    return PopularMovieShow(
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

internal fun Top250MovieShowEntity.toDomain(): Top250MovieShow {
    return Top250MovieShow(
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