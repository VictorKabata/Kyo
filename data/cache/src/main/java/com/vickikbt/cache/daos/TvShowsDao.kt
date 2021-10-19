package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.InTheatersComingSoonEntity
import com.vickikbt.cache.models.PopularMovieShowEntity
import com.vickikbt.cache.models.Top250MovieShowEntity
import com.vickikbt.commons.Constants

@Dao
interface TvShowsDao {

    @Query("SELECT * FROM In_Theaters_Movies_Table WHERE category=:category")
    suspend fun getComingSoon(category: String = Constants.COMING_SOON): List<InTheatersComingSoonEntity>

    @Query("SELECT * FROM Popular_Movies_Table WHERE category=:category")
    suspend fun getPopularTvShows(category: String = Constants.POPULAR_TV_SHOW): List<PopularMovieShowEntity>

    @Query("SELECT * FROM Top_250_Movies_Table WHERE category=:category")
    suspend fun getTop250TvShows(category: String = Constants.TOP_250_TV_SHOW): List<Top250MovieShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComingSoon(comingSoonEntities: List<InTheatersComingSoonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularTvShows(popularTvShowsEntities: List<PopularMovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTop250TvSHows(top250TvShowsEntities: List<Top250MovieShowEntity>)

    @Query("SELECT COUNT(*) FROM In_Theaters_Movies_Table WHERE category=:category")
    suspend fun isComingSoonCacheAvailable(category: String = Constants.COMING_SOON): Int

    @Query("SELECT COUNT(*) FROM Popular_Movies_Table WHERE category=:category")
    suspend fun isPopularTvShowCacheAvailable(category: String = Constants.POPULAR_TV_SHOW): Int

    @Query("SELECT COUNT(*) FROM Top_250_Movies_Table WHERE category=:category")
    suspend fun isTop250TvShowsCacheAvailable(category: String = Constants.TOP_250_TV_SHOW): Int

}