package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository

class GetTop250MoviesUseCase constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.fetchTop250Movies()

}