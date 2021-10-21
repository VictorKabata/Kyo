package com.vickikbt.mappers

import com.google.common.truth.Truth.assertThat
import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.network.models.ActorDto
import com.vickikbt.network.models.PlotShortDto
import com.vickikbt.network.models.TrailerResponseDto
import com.vickikbt.repository.mappers.toDomain
import org.junit.Test

class DtoToDomainTest {

    @Test
    fun actorDto_toDomain() {
        val actorDto = ActorDto("character", "id", "image", "name")
        val actor = Actor("character", "id", "image", "name")

        assertThat(actorDto.toDomain()).isEqualTo(actor)
    }

    @Test
    fun plotShortDto_toDomain() {
        val plotShortDto = PlotShortDto("html", "plain")
        val plotShort = PlotShort("html", "plain")

        assertThat(plotShortDto.toDomain()).isEqualTo(plotShort)
    }

    @Test
    fun trailerResponseDto_toDomain() {
        val trailerResponseDto = TrailerResponseDto(
            "error",
            "title",
            "id",
            "link",
            "link",
            "thumbnail",
            "title",
            "type",
            "upload",
            "description",
            "id",
            "title",
            "year"
        )

        val trailerResponse = TrailerResponse(
            "error",
            "title",
            "id",
            "link",
            "link",
            "thumbnail",
            "title",
            "type",
            "upload",
            "description",
            "id",
            "title",
            "year"
        )

        assertThat(trailerResponseDto.toDomain()).isEqualTo(trailerResponse)
    }

}