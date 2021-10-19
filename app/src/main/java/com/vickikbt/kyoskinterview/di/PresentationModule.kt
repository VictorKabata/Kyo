package com.vickikbt.kyoskinterview.di

import com.vickikbt.kyoskinterview.ui.fragments.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { MoviesViewModel(get(), get(), get()) }
}