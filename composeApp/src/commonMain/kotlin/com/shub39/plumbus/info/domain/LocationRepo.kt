package com.shub39.plumbus.info.domain

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface LocationRepo {
    suspend fun searchLocation(query: String): Result<List<Location>, DataError.Remote>
    suspend fun getSingleLocation(url: String): Result<Location, DataError.Remote>
    suspend fun getLocation(id: Int): Result<Location, DataError.Local>
    fun getLocations(): Flow<List<Location>>
    fun getFavLocations(): Flow<List<Location>>
    suspend fun addLocation(location: Location): EmptyResult<DataError.Local>
    suspend fun deleteLocation(id: Int)
    suspend fun setFavLocation(id: Int)
}