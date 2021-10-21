package com.vickikbt.kyoskinterview.utils

import com.vickikbt.domain.models.MovieShow

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<MovieShow>) : UiState()
    data class Error(val error: String) : UiState()
}
