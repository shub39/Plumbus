package com.shub39.plumbus.info.data.network

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.data.dto.character.CharacterResponse

interface RemoteCharacterDataSource {
    suspend fun searchCharacter(query: String): Result<CharacterResponse, DataError.Remote>
}