package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.InTheatersMoviesConverter
import com.vickikbt.cache.converters.PopularMoviesConverter
import com.vickikbt.cache.daos.MoviesDao
import com.vickikbt.cache.models.InTheatersMovieEntity
import com.vickikbt.cache.models.PopularMovieEntity
import com.vickikbt.cache.models.Top250MovieEntity

@Database(
    entities = [InTheatersMovieEntity::class, PopularMovieEntity::class, Top250MovieEntity::class],
    version = 1
)
@TypeConverters(
    InTheatersMoviesConverter::class,
    PopularMoviesConverter::class,
    Top250MovieEntity::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}