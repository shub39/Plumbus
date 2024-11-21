package com.shub39.plumbus.info.data.network

import com.shub39.plumbus.core.data.safeCall
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.data.dto.character.CharacterResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://rickandmortyapi.com/api"

class KtorRemoteCharacterDataSource(
    private val httpClient: HttpClient
): RemoteCharacterDataSource {
    override suspend fun searchCharacter(
        query: String
    ): Result<CharacterResponse, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/character/"
            ) {
                parameter("name", query)
            }
        }
    }
}