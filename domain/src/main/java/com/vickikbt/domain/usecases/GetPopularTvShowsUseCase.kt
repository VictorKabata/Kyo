package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.domain.repositories.TvShowsRepository

class GetPopularTvShowsUseCase constructor(private val tvShowsRepository: TvShowsRepository) {

    suspend operator fun invoke() = tvShowsRepository.fetchPopularTvShows()

}