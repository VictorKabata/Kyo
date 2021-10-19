package com.vickikbt.kyoskinterview.ui.fragments.tv_shows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.domain.models.InTheatersComingSoon
import com.vickikbt.domain.models.PopularMovieShow
import com.vickikbt.domain.models.Top250MovieShow
import com.vickikbt.domain.usecases.*
import kotlinx.coroutines.launch
import timber.log.Timber

class TvShowsViewModel constructor(
    private val getComingSoonUseCase: GetComingSoonUseCase,
    private val getPopularTvShowsUseCase: GetPopularTvShowsUseCase,
    private val getTop250TvShowUseCase: GetTop250TvShowUseCase
) : ViewModel() {

    private val _comingSoon = MutableLiveData<List<InTheatersComingSoon>>()
    val comingSoon get() = _comingSoon

    private val _popularTvShows = MutableLiveData<List<PopularMovieShow>>()
    val popularTvShows get() = _popularTvShows

    private val _top250TvShows = MutableLiveData<List<Top250MovieShow>>()
    val top250TvShows get() = _top250TvShows

    init {
        getComingSoon()
        getPopularTvShows()
        getTop250TvShows()
    }

    private fun getComingSoon() {
        try {
            viewModelScope.launch {
                val response = getComingSoonUseCase.invoke()
                _comingSoon.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    private fun getPopularTvShows() {
        try {
            viewModelScope.launch {
                val response = getPopularTvShowsUseCase.invoke()
                _popularTvShows.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

    private fun getTop250TvShows() {
        try {
            viewModelScope.launch {
                val response = getTop250TvShowUseCase.invoke()
                _top250TvShows.value = response
                return@launch
            }
        } catch (e: Exception) {
            Timber.e("Error: ${e.message}")
        }
    }

}