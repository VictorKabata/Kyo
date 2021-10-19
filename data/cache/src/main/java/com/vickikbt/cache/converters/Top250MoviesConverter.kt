package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.Top250MovieShowEntity

class Top250MoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(top250MovieEntities: List<Top250MovieShowEntity>): String? {
        if (top250MovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Top250MovieShowEntity>?>() {}.type
        return gson.toJson(top250MovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<Top250MovieShowEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Top250MovieShowEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}