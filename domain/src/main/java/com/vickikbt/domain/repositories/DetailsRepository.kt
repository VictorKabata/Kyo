package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse

interface DetailsRepository {

    suspend fun getPlot(id: String): PlotShort

    suspend fun getTrailer(id: String): TrailerResponse

    suspend fun getCast(id: String): List<Actor>

}