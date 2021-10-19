package com.vickikbt.domain.di

import com.vickikbt.domain.usecases.*
import org.koin.dsl.module

val domainModule = module {
    single { GetInTheaterMoviesUseCase(get()) }
    single { GetPopularMoviesUseCase(get()) }
    single { GetTop250MoviesUseCase(get()) }

    single { GetComingSoonUseCase(get()) }
    single { GetPopularTvShowsUseCase(get()) }
    single { GetTop250TvShowUseCase(get()) }
}