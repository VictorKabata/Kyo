package com.vickikbt.mappers

import com.google.common.truth.Truth.assertThat
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.network.models.MovieShowDto
import com.vickikbt.repository.mappers.toEntity
import org.junit.Test

class DtoToEntityTest {


    @Test
    fun movieShowDto_toEntity() {
        val movieShowDto = MovieShowDto("id", "rating", "count", "image", "title", "category")
        val movieShowEntity = MovieShowEntity("id", "rating", "count", "image", "title", "category")

        assertThat(movieShowDto.toEntity("wrong_category")).isNotEqualTo(movieShowEntity)
        assertThat(movieShowDto.toEntity("category")).isEqualTo(movieShowEntity)
    }
}