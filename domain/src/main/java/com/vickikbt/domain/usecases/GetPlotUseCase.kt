package com.vickikbt.domain.usecases

import com.vickikbt.domain.repositories.DetailsRepository

class GetPlotUseCase constructor(private val detailRepository: DetailsRepository) {

    suspend operator fun invoke(id: String) = detailRepository.getPlot(id)

}