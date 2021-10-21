package com.vickikbt.mappers

import com.google.common.truth.Truth.assertThat
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.repository.mappers.toDomain
import org.junit.Test

class EntityToDomainTest {


    @Test
    fun movieShowEntity_toDomain() {
        val movieShowEntity = MovieShowEntity("id", "rating", "count", "image", "title", "category")
        val movieShow = MovieShow("id", "rating", "count", "image", "title")

        assertThat(movieShowEntity.toDomain()).isEqualTo(movieShow)
    }
}