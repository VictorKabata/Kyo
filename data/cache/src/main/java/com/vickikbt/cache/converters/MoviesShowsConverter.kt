package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.MovieShowEntity

class MoviesShowsConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(movieEntities: List<MovieShowEntity>): String? {
        if (movieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<MovieShowEntity>?>() {}.type
        return gson.toJson(movieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<MovieShowEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<MovieShowEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}