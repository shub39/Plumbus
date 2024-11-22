package com.shub39.plumbus.info.domain

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface EpisodeRepo {
    suspend fun searchEpisode(query: String): Result<List<Episode>, DataError.Remote>
    suspend fun getSingleEpisode(url: String): Result<Episode, DataError.Remote>
    suspend fun getEpisode(id: Int): Result<Episode, DataError.Local>
    fun getEpisodes(): Flow<List<Episode>>
    fun getFavEpisodes(): Flow<List<Episode>>
    suspend fun addEpisode(episode: Episode): EmptyResult<DataError.Local>
    suspend fun removeEpisode(id: Int)
    suspend fun setFavEpisode(id: Int)
}