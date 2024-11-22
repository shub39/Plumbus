package com.shub39.plumbus.info.data.repository

import androidx.sqlite.SQLiteException
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.core.domain.map
import com.shub39.plumbus.info.data.database.EpisodeDao
import com.shub39.plumbus.info.data.mappers.toEpisode
import com.shub39.plumbus.info.data.mappers.toEpisodeEntity
import com.shub39.plumbus.info.data.network.RemoteDataSource
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.domain.EpisodeRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultEpisodeRepo(
    private val remoteDataSource: RemoteDataSource,
    private val episodeDao: EpisodeDao
): EpisodeRepo {
    override suspend fun searchEpisode(query: String): Result<List<Episode>, DataError.Remote> {
        return remoteDataSource
            .searchEpisode(query)
            .map { dto ->
                dto.results.map { it.toEpisode() }
            }
    }

    override fun getEpisodes(): Flow<List<Episode>> {
        return episodeDao
            .getEpisodes()
            .map { entities ->
                entities.map { it.toEpisode() }
            }
    }

    override fun getFavEpisodes(): Flow<List<Episode>> {
        return episodeDao
            .getFavEpisodes()
            .map { entities ->
                entities.map { it.toEpisode() }
            }
    }

    override suspend fun addEpisode(episode: Episode): EmptyResult<DataError.Local> {
        return try {
            episodeDao.upsertEpisode(episode.toEpisodeEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun removeEpisode(id: Int) {
        episodeDao.deleteEpisode(id)
    }

    override suspend fun setFavEpisode(id: Int) {
       episodeDao.setFavEpisode(id)
    }
}