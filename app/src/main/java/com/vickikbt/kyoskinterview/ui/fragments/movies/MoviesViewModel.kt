package com.vickikbt.kyoskinterview.ui.fragments.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.InTheatersComingSoonMovie
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow
import com.vickikbt.domain.usecases.GetInTheaterMoviesUseCase
import com.vickikbt.domain.usecases.GetPopularMoviesUseCase
import com.vickikbt.domain.usecases.GetTop250MoviesUseCase
import kotlinx.coroutines.launch
import timber.log.Timber

class MoviesViewModel constructor(
    private val getInTheaterMoviesUseCase: GetInTheaterMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase
) : ViewModel() {

    private val _inTheatersMovies = MutableLiveData<List<InTheatersComingSoonMovie>>()
    val inTheatersMovies get() = _inTheatersMovies

    private val _popularMovies = MutableLiveData<List<PopularMovieShow>>()
    val popularMovies get() = _popularMovies

    private val _top250Movies = MutableLiveData<List<Top250MovieShow>>()
    val top250Movies get() = _top250Movies

    init {
        getInTheaterMovies()
        getPopularMovies()
        getTop250Movies()
    }

    private fun getInTheaterMovies() {
        try {
            viewModelScope.launch {
                val response = getInTheaterMoviesUseCase.invoke()
                _inTheatersMovies.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    private fun getPopularMovies() {
        try {
            viewModelScope.launch {
                val response = getPopularMoviesUseCase.invoke()
                _popularMovies.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    private fun getTop250Movies() {
        try {
            viewModelScope.launch {
                val response = getTop250MoviesUseCase.invoke()
                _top250Movies.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

}