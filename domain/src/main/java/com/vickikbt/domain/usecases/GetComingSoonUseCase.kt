package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.domain.repositories.TvShowsRepository

class GetComingSoonUseCase constructor(private val tvShowsRepository: TvShowsRepository) {

    suspend operator fun invoke()=tvShowsRepository.fetchComingSoon()

}