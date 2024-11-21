package com.shub39.plumbus.info.data.network

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.data.dto.character.CharacterResponse
import com.shub39.plumbus.info.data.dto.episode.EpisodeResponse
import com.shub39.plumbus.info.data.dto.location.LocationResponse

interface RemoteDataSource {
    suspend fun searchCharacter(query: String): Result<CharacterResponse, DataError.Remote>
    suspend fun searchEpisode(query: String): Result<EpisodeResponse, DataError.Remote>
    suspend fun searchLocation(query: String): Result<LocationResponse, DataError.Remote>
}