package com.shub39.plumbus.info.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PlumbusConverters::class)
@ConstructedBy(DbConstructor::class)
abstract class PlumbusDb: RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val episodeDao: EpisodeDao
    abstract val locationDao: LocationDao

    companion object {
        const val DATABASE_NAME = "plumbus.db"
    }
}