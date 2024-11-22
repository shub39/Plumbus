package com.shub39.plumbus.info.data.repository

import androidx.sqlite.SQLiteException
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.core.domain.map
import com.shub39.plumbus.info.data.database.CharacterDao
import com.shub39.plumbus.info.data.mappers.toCharacter
import com.shub39.plumbus.info.data.mappers.toCharacterEntity
import com.shub39.plumbus.info.data.network.RemoteDataSource
import com.shub39.plumbus.info.domain.CharacterRepo
import com.shub39.plumbus.info.domain.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultCharacterRepo(
    private val remoteDataSource: RemoteDataSource,
    private val characterDao: CharacterDao
) : CharacterRepo {
    override suspend fun searchCharacter(query: String): Result<List<Character>, DataError.Remote> {
        return remoteDataSource
            .searchCharacter(query)
            .map { dto ->
                dto.results.map { it.toCharacter() }
            }
    }

    override fun getCharacters(): Flow<List<Character>> {
        return characterDao
            .getCharacters()
            .map { entities ->
                entities.map { it.toCharacter() }
            }
    }

    override fun getFavCharacters(): Flow<List<Character>> {
        return characterDao
            .getFavCharacters()
            .map { entities ->
                entities.map { it.toCharacter() }
            }
    }

    override suspend fun addCharacter(character: Character): EmptyResult<DataError.Local> {
        return try {
            characterDao.upsertCharacter(character.toCharacterEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun removeCharacter(id: Int) {
        characterDao.deleteCharacter(id)
    }

    override suspend fun setFavCharacter(id: Int) {
        characterDao.setFavCharacter(id)
    }
}