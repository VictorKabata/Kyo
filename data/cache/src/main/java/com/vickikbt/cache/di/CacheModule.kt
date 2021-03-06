package com.vickikbt.cache.di

import androidx.room.Room
import com.vickikbt.cache.AppDatabase
import com.vickikbt.cache.preferences.ThemePreferences
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

    single {
        ThemePreferences(androidApplication())
    }
}
