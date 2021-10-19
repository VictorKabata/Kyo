package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository

class GetPopularMoviesUseCase constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.fetchPopularMovies()

}