package com.shub39.plumbus.info.data.repository

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.core.domain.map
import com.shub39.plumbus.info.data.mappers.toCharacter
import com.shub39.plumbus.info.data.network.RemoteCharacterDataSource
import com.shub39.plumbus.info.domain.character.CharacterRepo
import com.shub39.plumbus.info.domain.character.Character

class DefaultCharacterRepo(
    private val remoteCharacterDataSource: RemoteCharacterDataSource
): CharacterRepo {
    override suspend fun searchCharacter(query: String): Result<List<Character>, DataError.Remote> {
        return remoteCharacterDataSource
            .searchCharacter(query)
            .map { dto ->
                dto.results.map { it.toCharacter() }
            }
    }
}