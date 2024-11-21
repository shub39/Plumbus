package com.shub39.plumbus.info.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Upsert
    suspend fun upsertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<CharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacter(id: Int): CharacterEntity?

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacter(id: Int)

    @Query("SELECT * FROM characters WHERE url = :url")
    suspend fun getCharacter(url: String): CharacterEntity?
}