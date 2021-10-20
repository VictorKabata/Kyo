package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.domain.models.MovieShow

internal fun MovieShowEntity.toDomain(): MovieShow {
    return MovieShow(
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        title = this.title,
    )
}