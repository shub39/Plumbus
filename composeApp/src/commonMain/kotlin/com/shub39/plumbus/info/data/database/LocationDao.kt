package com.shub39.plumbus.info.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Upsert
    suspend fun upsertLocation(location: LocationEntity)

    @Query("SELECT * FROM locations")
    fun getLocations(): Flow<List<LocationEntity>>

    @Query("DELETE FROM locations WHERE id = :id")
    suspend fun deleteLocation(id: Int)

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocation(id: Int): LocationEntity?

    @Query("SELECT * FROM locations WHERE url = :url")
    suspend fun getLocation(url: String): LocationEntity?
}