package com.vickikbt.kyoskinterview.ui.fragments.all_content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vickikbt.domain.usecases.GetMoviesShowsByCategoryUseCase
import kotlinx.coroutines.flow.collect
import okio.IOException
import timber.log.Timber

class AllContentViewModel constructor(private val getMoviesShowsByCategoryUseCaseUseCase: GetMoviesShowsByCategoryUseCase) :
    ViewModel() {

    fun getMoviesShowsByCategory(category: String) = liveData {
        try {
            val response = getMoviesShowsByCategoryUseCaseUseCase.invoke(category)
            response.collect {
                emit(it)
            }
        } catch (e: IOException) {
            Timber.e("Error: ${e.localizedMessage}")
        }
    }

}