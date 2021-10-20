package com.vickikbt.repository.mappers

import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.network.models.ActorDto
import com.vickikbt.network.models.PlotShortDto
import com.vickikbt.network.models.TrailerResponseDto

internal fun ActorDto.toDomain(): Actor {
    return Actor(
        asCharacter = this.asCharacter,
        id = this.id,
        image = this.image,
        name = this.name
    )
}

internal fun PlotShortDto.toDomain(): PlotShort {
    return PlotShort(html = this.html, plainText = this.plainText)
}

internal fun TrailerResponseDto.toDomain(): TrailerResponse {
    return TrailerResponse(
        errorMessage = this.errorMessage,
        fullTitle = this.fullTitle,
        imDbId = this.imDbId,
        link = this.link,
        linkEmbed = this.linkEmbed,
        thumbnailUrl = this.thumbnailUrl,
        title = this.title,
        type = this.type,
        uploadDate = this.uploadDate,
        videoDescription = this.videoDescription,
        videoId = this.videoId,
        videoTitle = this.videoTitle,
        year = this.year
    )
}