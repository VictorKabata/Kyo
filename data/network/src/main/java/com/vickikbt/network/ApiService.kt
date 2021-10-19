package com.vickikbt.network

import com.vickikbt.commons.Constants
import com.vickikbt.network.models.InTheatersResponseDto
import com.vickikbt.network.models.PopularMoviesResponseDto
import com.vickikbt.network.models.Top250MoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("InTheaters/{apiKey}")
    suspend fun fetchInTheaterMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<InTheatersResponseDto>

    @GET("MostPopularMovies/{apiKey}")
    suspend fun fetchPopularMovies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<PopularMoviesResponseDto>

    @GET("Top250Movies/{apiKey}")
    suspend fun fetchTop250Movies(@Path("apiKey") apiKey: String = Constants.API_KEY): Response<Top250MoviesResponseDto>

}