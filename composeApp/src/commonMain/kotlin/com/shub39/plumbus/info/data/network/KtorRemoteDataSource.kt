package com.shub39.plumbus.info.data.network

import com.shub39.plumbus.core.data.safeCall
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.data.dto.character.CharacterDto
import com.shub39.plumbus.info.data.dto.character.CharacterResponse
import com.shub39.plumbus.info.data.dto.episode.EpisodeDto
import com.shub39.plumbus.info.data.dto.episode.EpisodeResponse
import com.shub39.plumbus.info.data.dto.location.LocationDto
import com.shub39.plumbus.info.data.dto.location.LocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://rickandmortyapi.com/api"

class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : RemoteDataSource {
    override suspend fun searchCharacter(query: String): Result<CharacterResponse, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/character/"
            ) {
                parameter("name", query)
            }
        }
    }

    override suspend fun getSingleCharacter(url: String): Result<CharacterDto, DataError.Remote> {
       return safeCall {
           httpClient.get(
               urlString = url
           )
       }
    }

    override suspend fun searchEpisode(query: String): Result<EpisodeResponse, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/episode/"
            ) {
                parameter("name", query)
            }
        }
    }

    override suspend fun getSingleEpisode(url: String): Result<EpisodeDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = url
            )
        }
    }

    override suspend fun searchLocation(query: String): Result<LocationResponse, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = "$BASE_URL/location/"
            ) {
                parameter("name", query)
            }
        }
    }

    override suspend fun getSingleLocation(url: String): Result<LocationDto, DataError.Remote> {
        return safeCall {
            httpClient.get(
                urlString = url
            )
        }
    }
}