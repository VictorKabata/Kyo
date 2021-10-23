package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.MovieShow
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {

    suspend fun fetchComingSoon(): Flow<List<MovieShow>>

    suspend fun fetchPopularTvShows(): Flow<List<MovieShow>>

    suspend fun fetchTop250TvShows(): Flow<List<MovieShow>>

}