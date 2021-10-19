package com.vickikbt.domain.repositories

import com.vickikbt.domain.models.InTheatersComingSoon
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow

interface TvShowsRepository {

    suspend fun fetchComingSoon(): List<InTheatersComingSoon>

    suspend fun fetchPopularTvShows(): List<PopularMovieShow>

    suspend fun fetchTop250TvShows(): List<Top250MovieShow>

}