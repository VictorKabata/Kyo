package com.vickikbt.repository.di

import com.vickikbt.domain.repositories.MoviesRepository
import com.vickikbt.repository.data_sources.MoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}