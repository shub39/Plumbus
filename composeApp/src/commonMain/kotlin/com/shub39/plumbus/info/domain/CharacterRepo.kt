package com.shub39.plumbus.info.domain

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.EmptyResult
import com.shub39.plumbus.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface CharacterRepo {
    suspend fun searchCharacter(query: String): Result<List<Character>, DataError.Remote>
    suspend fun getSingleCharacter(url: String): Result<Character, DataError.Remote>
    suspend fun getCharacter(id: Int): Result<Character, DataError.Local>
    fun getCharacters(): Flow<List<Character>>
    fun getFavCharacters(): Flow<List<Character>>
    suspend fun addCharacter(character: Character): EmptyResult<DataError.Local>
    suspend fun removeCharacter(id: Int)
    suspend fun setFavCharacter(id: Int)
}