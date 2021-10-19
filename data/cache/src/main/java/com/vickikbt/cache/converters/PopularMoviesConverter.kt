package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.PopularMovieShowEntity

class PopularMoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(popularMovieEntities: List<PopularMovieShowEntity>): String? {
        if (popularMovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<PopularMovieShowEntity>?>() {}.type
        return gson.toJson(popularMovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<PopularMovieShowEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<PopularMovieShowEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}