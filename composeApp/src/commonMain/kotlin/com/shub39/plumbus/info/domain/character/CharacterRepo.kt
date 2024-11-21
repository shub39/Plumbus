package com.shub39.plumbus.info.domain.character

import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result

interface CharacterRepo {
    suspend fun searchCharacter(query: String): Result<List<Character>, DataError.Remote>
}