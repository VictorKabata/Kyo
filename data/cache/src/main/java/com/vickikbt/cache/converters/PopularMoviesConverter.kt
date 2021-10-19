package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.InTheatersMovieEntity
import com.vickikbt.cache.models.PopularMovieEntity

class PopularMoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(popularMovieEntities: List<PopularMovieEntity>): String? {
        if (popularMovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<PopularMovieEntity>?>() {}.type
        return gson.toJson(popularMovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<PopularMovieEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<PopularMovieEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}