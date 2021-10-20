package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.MovieShowEntity

@Dao
interface MoviesShowsDao {

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getMoviesShows(category: String): List<MovieShowEntity>

    @Query("SELECT * FROM Movies_Shows_Table WHERE id=:id")
    suspend fun getMoviesShowsById(id: String): MovieShowEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMoviesShows(inTheatersEntities: List<MovieShowEntity>)

    @Query("DELETE FROM Movies_Shows_Table WHERE category=:category")
    suspend fun deleteMovieShows(category: String)

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isMovieShowCacheAvailable(category: String? = null): Int

}