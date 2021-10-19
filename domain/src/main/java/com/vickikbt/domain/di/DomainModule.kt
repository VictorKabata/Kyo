package com.vickikbt.domain.di

import com.vickikbt.domain.usecases.GetInTheaterMovies
import com.vickikbt.domain.usecases.GetPopularMovies
import com.vickikbt.domain.usecases.GetTop250Movies
import org.koin.dsl.module

val domainModule = module {
    single { GetInTheaterMovies(get()) }
    single { GetPopularMovies(get()) }
    single { GetTop250Movies(get()) }
}