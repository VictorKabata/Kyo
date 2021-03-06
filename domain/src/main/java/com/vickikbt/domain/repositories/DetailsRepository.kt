package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun getPlot(id: String): PlotShort

    suspend fun getTrailer(id: String): TrailerResponse

    suspend fun getCast(id: String): List<Actor>

    suspend fun getMovieShowById(id:String): Flow<MovieShow>

    suspend fun getMoviesShowsByCategory(category:String): Flow<List<MovieShow>>

}