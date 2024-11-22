package com.shub39.plumbus.info.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {
    @Upsert
    suspend fun upsertEpisode(episode: EpisodeEntity)

    @Query("SELECT * FROM episodes")
    fun getEpisodes(): Flow<List<EpisodeEntity>>

    @Query("UPDATE episodes SET isFav = NOT isFav WHERE id = :id")
    suspend fun setFavEpisode(id: Int)

    @Query("SELECT * FROM episodes WHERE isFav = 1")
    fun getFavEpisodes(): Flow<List<EpisodeEntity>>

    @Query("DELETE FROM episodes WHERE id = :id")
    suspend fun deleteEpisode(id: Int)

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisode(id: Int): EpisodeEntity?

    @Query("SELECT * FROM episodes WHERE url = :url")
    suspend fun getEpisode(url: String): EpisodeEntity?
}