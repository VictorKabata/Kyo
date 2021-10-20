package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.MovieShow

interface TvShowsRepository {

    suspend fun fetchComingSoon(): List<MovieShow>

    suspend fun fetchPopularTvShows(): List<MovieShow>

    suspend fun fetchTop250TvShows(): List<MovieShow>

}