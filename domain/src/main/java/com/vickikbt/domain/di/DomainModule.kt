package com.vickikbt.domain.di

import com.vickikbt.domain.usecases.GetInTheaterMoviesUseCase
import com.vickikbt.domain.usecases.GetPopularMoviesUseCase
import com.vickikbt.domain.usecases.GetTop250MoviesUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetInTheaterMoviesUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetTop250MoviesUseCase(get()) }
}