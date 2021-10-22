package com.vickikbt.kyoskinterview

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.vickikbt.cache.di.cacheModule
import com.vickikbt.cache.preferences.ThemePreferences
import com.vickikbt.domain.di.domainModule
import com.vickikbt.kyoskinterview.di.presentationModule
import com.vickikbt.network.di.networkModule
import com.vickikbt.repository.di.repositoryModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class KyoskInterviewApplication : Application() {

    private val themePreferences: ThemePreferences by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin()

        initTheme()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules =
            listOf(domainModule, networkModule, cacheModule, repositoryModule, presentationModule)

        startKoin {
            androidLogger(level = Level.NONE)
            androidContext(this@KyoskInterviewApplication)
            modules(modules)
        }
    }

    private fun initTheme() {
        val appTheme = themePreferences.appTheme
        appTheme.observeForever { theme ->
            when (theme) {
                "light_theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                "dark_theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                "system_default" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

}