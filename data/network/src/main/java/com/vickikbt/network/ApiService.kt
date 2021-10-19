package com.vickikbt.network

import com.vickikbt.commons.Constants
import com.vickikbt.network.models.InTheatersComingSoonResponseDto
import com.vickikbt.network.models.PopularMoviesShowsResponseDto
import com.vickikbt.network.models.Top250MoviesShowsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("InTheaters/{apiKey}")
    suspend fun fetchInTheaterMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<InTheatersComingSoonResponseDto>

    @GET("MostPopularMovies/{apiKey}")
    suspend fun fetchPopularMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<PopularMoviesShowsResponseDto>

    @GET("Top250Movies/{apiKey}")
    suspend fun fetchTop250Movies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<Top250MoviesShowsResponseDto>

}