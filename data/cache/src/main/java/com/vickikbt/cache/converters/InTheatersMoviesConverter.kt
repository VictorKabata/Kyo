package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.InTheatersMovieEntity

class InTheatersMoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(inTheaterMovieEntities: List<InTheatersMovieEntity>): String? {
        if (inTheaterMovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<InTheatersMovieEntity>?>() {}.type
        return gson.toJson(inTheaterMovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<InTheatersMovieEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<InTheatersMovieEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}