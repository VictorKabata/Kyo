package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.MoviesShowsConverter
import com.vickikbt.cache.daos.MoviesDao
import com.vickikbt.cache.daos.TvShowsDao
import com.vickikbt.cache.models.MovieShowEntity

@Database(
    entities = [MovieShowEntity::class],
    version = 1
)
@TypeConverters(MoviesShowsConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao

}