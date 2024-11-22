package com.shub39.plumbus.info.data.repository

import androidx.sqlite.SQLiteException
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.core.domain.map
import com.shub39.plumbus.info.data.database.LocationDao
import com.shub39.plumbus.info.data.mappers.toLocation
import com.shub39.plumbus.info.data.mappers.toLocationEntity
import com.shub39.plumbus.info.data.network.RemoteDataSource
import com.shub39.plumbus.info.domain.Location
import com.shub39.plumbus.info.domain.LocationRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultLocationRepo(
    private val remoteDataSource: RemoteDataSource,
    private val locationDao: LocationDao
): LocationRepo {
    override suspend fun searchLocation(query: String): Result<List<Location>, DataError.Remote> {
        return remoteDataSource
            .searchLocation(query)
            .map { dto ->
                dto.results.map { it.toLocation() }
            }
    }

    override fun getLocations(): Flow<List<Location>> {
        return locationDao
            .getLocations()
            .map { entities ->
                entities.map { it.toLocation() }
            }
    }

    override fun getFavLocations(): Flow<List<Location>> {
        return locationDao
            .getFavLocations()
            .map { entities ->
                entities.map { it.toLocation() }
            }
    }

    override suspend fun addLocation(location: Location): EmptyResult<DataError.Local> {
        return try {
            locationDao.upsertLocation(location.toLocationEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteLocation(id: Int) {
        locationDao.deleteLocation(id)
    }

    override suspend fun setFavLocation(id: Int) {
        locationDao.setFavLocation(id)
    }

}