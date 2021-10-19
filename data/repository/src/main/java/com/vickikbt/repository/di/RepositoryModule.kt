package com.vickikbt.repository.di

import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.domain.repositories.TvShowsRepository
import com.vickikbt.repository.data_sources.MoviesRepositoryImpl
import com.vickikbt.repository.data_sources.TvShowsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }

    single<TvShowsRepository> { TvShowsRepositoryImpl(get(), get()) }
}