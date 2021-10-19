package com.vickikbt.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vickikbt.cache.converters.InTheatersMoviesConverter
import com.vickikbt.cache.converters.PopularMoviesConverter
import com.vickikbt.cache.converters.Top250MoviesConverter
import com.vickikbt.cache.daos.MoviesDao
import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity

@Database(
    entities = [InTheatersComingSoonEntity::class, PopularMovieShowEntity::class, Top250MovieShowEntity::class],
    version = 1
)
@TypeConverters(
    InTheatersMoviesConverter::class,
    PopularMoviesConverter::class,
    Top250MoviesConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}