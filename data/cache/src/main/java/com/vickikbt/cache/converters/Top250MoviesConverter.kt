package com.vickikbt.cache.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vickikbt.cache.models.Top250MovieEntity

class Top250MoviesConverter {

    private val gson = Gson()

    @TypeConverter
    fun from(top250MovieEntities: List<Top250MovieEntity>): String? {
        if (top250MovieEntities.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Top250MovieEntity>?>() {}.type
        return gson.toJson(top250MovieEntities, type)
    }

    @TypeConverter
    fun to(movieEntityString: String?): List<Top250MovieEntity>? {
        if (movieEntityString.isNullOrEmpty()) return null

        val type = object : TypeToken<List<Top250MovieEntity>?>() {}.type
        return gson.fromJson(movieEntityString, type)
    }

}