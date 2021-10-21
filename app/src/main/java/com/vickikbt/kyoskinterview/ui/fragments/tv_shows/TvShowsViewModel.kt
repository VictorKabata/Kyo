package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.usecases.GetComingSoonUseCase
import com.vickikbt.domain.usecases.GetPopularTvShowsUseCase
import com.vickikbt.domain.usecases.GetTop250TvShowUseCase
import com.vickikbt.kyoskinterview.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okio.IOException
import java.net.UnknownHostException

class TvShowsViewModel constructor(
    private val getComingSoonUseCase: GetComingSoonUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getTop250TvShowUseCase: GetTop250TvShowUseCase
) : ViewModel() {

    private val _comingSoon = MutableStateFlow<UiState>(UiState.Loading)
    val comingSoon: StateFlow<UiState> = _comingSoon

    private val _popularTvShows = MutableStateFlow<UiState>(UiState.Loading)
    val popularTvShows: StateFlow<UiState> = _popularTvShows

    private val _top250TvShows = MutableStateFlow<UiState>(UiState.Loading)
    val top250TvShows: StateFlow<UiState> = _top250TvShows

    init {
        getComingSoon()
        getPopularTvShows()
        getTop250TvShows()
    }

    private fun getComingSoon() = viewModelScope.launch {
        try {
            val response = getComingSoonUseCase.invoke()
            response.collect {
                _comingSoon.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _comingSoon.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _comingSoon.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occured")
        }
    }

    private fun getPopularTvShows() = viewModelScope.launch {
        try {
            val response = getPopularTvShowsUseCase.invoke()
            response.collect {
                _popularTvShows.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _popularTvShows.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _popularTvShows.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occured")
        }
    }

    private fun getTop250TvShows() = viewModelScope.launch {
        try {
            val response = getTop250TvShowUseCase.invoke()
            response.collect {
                _top250TvShows.value = UiState.Success(it)
            }
        } catch (e: UnknownHostException) {
            _comingSoon.value =
                UiState.Error(e.localizedMessage ?: "Check your internet connection")
        } catch (e: IOException) {
            _comingSoon.value =
                UiState.Error(e.localizedMessage ?: "An unknown error occured")
        }
    }

}