package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getInTheatersMovies(category: String = Constants.IN_THEATERS): List<MovieShowEntity>

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getPopularMovies(category: String = Constants.POPULAR_MOVIE): List<MovieShowEntity>

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getTop250Movies(category: String = Constants.TOP_250_MOVIE): List<MovieShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInTheatersMovies(inTheatersEntities: List<MovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(popularMoviesEntities: List<MovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTop250Movies(moviesEntities: List<MovieShowEntity>)

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isInTheaterMoviesCacheAvailable(category: String = Constants.IN_THEATERS): Int

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isPopularMoviesCacheAvailable(category: String = Constants.POPULAR_MOVIE): Int

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isTop250MoviesCacheAvailable(category: String = Constants.TOP_250_MOVIE): Int

}