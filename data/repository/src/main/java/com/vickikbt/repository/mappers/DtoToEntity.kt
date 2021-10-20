package com.vickikbt.repository.mappers

import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.network.models.MovieShowDto


internal fun MovieShowDto.toEntity(category: String?): MovieShowEntity {
    return MovieShowEntity(
        id = this.id,
        imDbRating = this.imDbRating,
        imDbRatingCount = this.imDbRatingCount,
        image = this.image,
        title = this.title,
        category = category
    )
}