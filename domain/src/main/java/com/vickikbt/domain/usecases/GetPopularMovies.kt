package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository

class GetPopularMovies constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.fetchPopularMovies()

}