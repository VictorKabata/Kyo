package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.DetailsRepository

class GetMoviesShowsByCategoryUseCase constructor(private val detailsRepository:DetailsRepository) {

    suspend operator fun invoke(category:String)=detailsRepository.getMoviesShowsByCategory(category)

}