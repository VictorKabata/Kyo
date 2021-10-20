package com.vickikbt.kyoskinterview.ui.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vickikbt.domain.usecases.GetCastUseCase
import com.vickikbt.domain.usecases.GetPlotUseCase
import com.vickikbt.domain.usecases.GetTrailerUseCase
import timber.log.Timber

class DetailsViewModel constructor(
    private val getPlotUseCase: GetPlotUseCase,
    private val getTrailerUseCase: GetTrailerUseCase,
    private val getCastUseCase: GetCastUseCase
) : ViewModel() {

    fun getPlot(id: String) = liveData {
        try {
            val response = getPlotUseCase.invoke(id)
            emit(response)
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    fun getTrailer(id: String) = liveData {
        try {
            val response = getTrailerUseCase.invoke(id)
            emit(response)
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    fun getCast(id: String) = liveData {
        try {
            val response = getCastUseCase.invoke(id)
            emit(response)
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

}