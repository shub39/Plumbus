package com.shub39.plumbus.info.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// cant think of a better name
object PlumbusConverters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromPair(value: Pair<String, String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toPair(value: String): Pair<String, String> {
        return Json.decodeFromString(value)
    }

}