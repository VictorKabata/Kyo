package com.vickikbt.repository.data_sources

import com.vickikbt.cache.AppDatabase
import com.vickikbt.commons.Resource
import com.vickikbt.domain.models.Actor
import com.vickikbt.domain.models.MovieShow
import com.vickikbt.domain.models.PlotShort
import com.vickikbt.domain.models.TrailerResponse
import com.vickikbt.domain.repositories.DetailsRepository
import com.vickikbt.network.ApiService
import com.vickikbt.repository.mappers.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class DetailsRepositoryImpl constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : DetailsRepository {

    override suspend fun getPlot(id: String): Resource<PlotShort> {
        return try {
            Resource.Loading(data = null)
            val networkResponse = apiService.fetchPlot(id = id)
            val plotShort = networkResponse.plotShort!!.toDomain()
            Resource.Success(data = plotShort)
        } catch (e: HttpException) {
            Resource.Error(message = e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred")
        }
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