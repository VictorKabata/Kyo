package com.vickikbt.repository.data_sources

import com.vickikbt.cache.AppDatabase
import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.domain.repositories.DetailsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.network.utils.SafeApiRequest
import com.vickikbt.repository.mappers.toDomain

class DetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : DetailsRepository,
    SafeApiRequest() {

    override suspend fun getPlot(id: String): PlotShort {
        val networkResponse = safeApiRequest { apiService.fetchPlot(id = id) }
        return networkResponse.plotShort!!.toDomain()
    }

    override suspend fun getTrailer(id: String): TrailerResponse {
        val networkResponse = safeApiRequest { apiService.fetchTrailer(id = id) }
        return networkResponse.toDomain()
    }

    override suspend fun getCast(id: String): List<Actor> {
        val networkResponse = safeApiRequest { apiService.fetchCast(id = id) }
        return networkResponse.actors!!.map { it.toDomain() }
    }

    override suspend fun getMovieShowById(id: String) =
        appDatabase.moviesDao().getMoviesShowsById(id).toDomain()
}