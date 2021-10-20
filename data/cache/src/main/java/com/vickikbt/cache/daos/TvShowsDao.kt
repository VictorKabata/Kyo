package com.vickikbt.cache.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vickikbt.cache.models.MovieShowEntity
import com.vickikbt.commons.Constants

@Dao
interface TvShowsDao {

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getComingSoon(category: String = Constants.COMING_SOON): List<MovieShowEntity>

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getPopularTvShows(category: String = Constants.POPULAR_TV_SHOW): List<MovieShowEntity>

    @Query("SELECT * FROM Movies_Shows_Table WHERE category=:category")
    suspend fun getTop250TvShows(category: String = Constants.TOP_250_TV_SHOW): List<MovieShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveComingSoon(comingSoonEntities: List<MovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePopularTvShows(popularTvShowsEntities: List<MovieShowEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTop250TvShows(tvShowsEntities: List<MovieShowEntity>)

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isComingSoonCacheAvailable(category: String = Constants.COMING_SOON): Int

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isPopularTvShowCacheAvailable(category: String = Constants.POPULAR_TV_SHOW): Int

    @Query("SELECT COUNT(*) FROM Movies_Shows_Table WHERE category=:category")
    suspend fun isTop250TvShowsCacheAvailable(category: String = Constants.TOP_250_TV_SHOW): Int

}