package com.vickikbt.kyoskinterview.ui.fragments.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.usecases.GetInTheaterMoviesUseCase
import com.vickikbt.domain.usecases.GetPopularMoviesUseCase
import com.vickikbt.domain.usecases.GetTop250MoviesUseCase
import com.vickikbt.kyoskinterview.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okio.IOException
import java.net.UnknownHostException

class MoviesViewModel constructor(
    private val getInTheaterMoviesUseCase: GetInTheaterMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTop250MoviesUseCase: GetTop250MoviesUseCase
) : ViewModel() {

    private val _inTheatersMovies = MutableStateFlow<UiState>(UiState.Loading)
    val inTheatersMovies: StateFlow<UiState> = _inTheatersMovies

    private val _popularMovies = MutableStateFlow<UiState>(UiState.Loading)
    val popularMovies: StateFlow<UiState> = _popularMovies

    private val _top250Movies = MutableStateFlow<UiState>(UiState.Loading)
    val top250Movies: StateFlow<UiState> = _top250Movies

    init {
        getTop250Movies()
        getPopularMovies()
        getInTheaterMovies()
    }

    private fun getInTheaterMovies() = viewModelScope.launch {
        try {
            val response = getInTheaterMoviesUseCase.invoke()
            response.collect {
                _inTheatersMovies.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _inTheatersMovies.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _inTheatersMovies.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occured")
        }
    }

    private fun getPopularMovies() = viewModelScope.launch {
        try {
            val response = getPopularMoviesUseCase.invoke()
            response.collect {
                _popularMovies.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _popularMovies.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _popularMovies.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occurred")
        }
    }

    private fun getTop250Movies() = viewModelScope.launch {
        try {
            val response = getTop250MoviesUseCase.invoke()
            response.collect {
                _top250Movies.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _top250Movies.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _top250Movies.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occured")
        }
    }

}