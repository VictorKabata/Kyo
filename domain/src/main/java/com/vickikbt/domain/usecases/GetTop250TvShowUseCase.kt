package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.TvShowsRepository

class GetTop250TvShowUseCase constructor(private val tvShowsRepository: TvShowsRepository) {

    suspend operator fun invoke() = tvShowsRepository.fetchTop250TvShows()

}