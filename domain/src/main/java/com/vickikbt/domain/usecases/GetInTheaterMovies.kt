package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.MoviesRepository

class GetInTheaterMovies constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke()=moviesRepository.fetchInTheatersMovies()

}