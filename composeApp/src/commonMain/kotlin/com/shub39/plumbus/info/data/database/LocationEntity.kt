package com.shub39.plumbus.info.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val isFav: Boolean
)
