package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.DetailsRepository

class GetMovieShowById constructor(private val detailsRepository: DetailsRepository) {

    suspend operator fun invoke(id: String) = detailsRepository.getMovieShowById(id)

}