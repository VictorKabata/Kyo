package com.vickikbt.network

import com.vickikbt.commons.Constants
import com.vickikbt.network.models.CastResponseDto
import com.vickikbt.network.models.MoviesShowsResponseDto
import com.vickikbt.network.models.PlotResponseDto
import com.vickikbt.network.models.TrailerResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //region Movies Endpoints
    @GET("InTheaters/{apiKey}")
    suspend fun fetchInTheaterMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto

    @GET("MostPopularMovies/{apiKey}")
    suspend fun fetchPopularMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto

    @GET("Top250Movies/{apiKey}")
    suspend fun fetchTop250Movies(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto
    //endregion

    //region TV Shows Endpoints
    @GET("ComingSoon/{apiKey}")
    suspend fun fetchComingSoon(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto

    @GET("MostPopularTVs/{apiKey}")
    suspend fun fetchPopularTvShows(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto

    @GET("Top250TVs/{apiKey}")
    suspend fun fetchTop250TvShows(@Path("apiKey") apiKey: String = Constants.API_KEY): MoviesShowsResponseDto
    //endregion

    //region Details Endpoints
    @GET("Wikipedia/{apiKey}/{id}")
    suspend fun fetchPlot(
        @Path("apiKey") apiKey: String = Constants.API_KEY,
        @Path("id") id: String
    ): PlotResponseDto

    @GET("Trailer/{apiKey}/{id}")
    suspend fun fetchTrailer(
        @Path("apiKey") apiKey: String = Constants.API_KEY,
        @Path("id") id: String
    ): TrailerResponseDto

    @GET("FullCast/{apiKey}/{id}")
    suspend fun fetchCast(
        @Path("apiKey") apiKey: String = Constants.API_KEY,
        @Path("id") id: String
    ): CastResponseDto
    //endregion
}