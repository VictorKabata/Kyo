package com.vickikbt.cache.di

import androidx.room.Room
import com.vickikbt.cache.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val cacheModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "KyoskInterview.db"
        ).fallbackToDestructiveMigration().build()
    }
}
