package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.*

@Dao
interface MoviesDao {

    @Query("SELECT * FROM In_Theaters_Movies_Table")
    suspend fun getInTheatersMovies(): List<InTheatersMovieEntity>

    @Query("SELECT * FROM Popular_Movies_Table")
    suspend fun getPopularMovies(): List<PopularMovieEntity>

    @Query("SELECT * FROM Top_250_Movies_Table")
    suspend fun getTop250Movies(): List<Top250MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveInTheatersMovies(inTheatersEntities: List<InTheatersMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularMovies(popularMoviesEntities: List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTop250Movies(top250MoviesEntities: List<Top250MovieEntity>)

}