package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.InTheatersComingSoonEntity

class InTheatersMoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(inTheaterMovieEntities: List<InTheatersComingSoonEntity>): String? {
        if (inTheaterMovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<InTheatersComingSoonEntity>?>() {}.type
        return gson.toJson(inTheaterMovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<InTheatersComingSoonEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<InTheatersComingSoonEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}