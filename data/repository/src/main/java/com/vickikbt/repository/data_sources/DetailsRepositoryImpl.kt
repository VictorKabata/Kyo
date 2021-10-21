package com.vickikbt.repository.data_sources

import com.vickikbt.cache.AppDatabase
import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.domain.repositories.DetailsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : DetailsRepository {

    override suspend fun getPlot(id: String): PlotShort {
        val networkResponse = apiService.fetchPlot(id = id)
        return networkResponse.plotShort!!.toDomain()
    }

    override suspend fun getTrailer(id: String): TrailerResponse {
        val networkResponse = apiService.fetchTrailer(id = id)
        return networkResponse.toDomain()
    }

    override suspend fun getCast(id: String): List<Actor> {
        val networkResponse = apiService.fetchCast(id = id)
        return networkResponse.actors!!.map { it.toDomain() }
    }

    override suspend fun getMovieShowById(id: String): Flow<MovieShow> {
        return appDatabase.moviesDao().getMoviesShowsById(id).map { it.toDomain() }
    }
}