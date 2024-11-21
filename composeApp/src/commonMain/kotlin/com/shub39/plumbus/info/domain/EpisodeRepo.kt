package com.shub39.plumbus.info.domain

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface EpisodeRepo {
    suspend fun searchEpisode(query: String): Result<List<Episode>, DataError.Remote>
    fun getEpisodes(): Flow<List<Episode>>
    suspend fun addEpisode(episode: Episode): EmptyResult<DataError.Local>
    suspend fun removeEpisode(id: Int)
}