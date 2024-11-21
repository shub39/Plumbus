package com.shub39.plumbus.info.data.mappers

import com.shub39.plumbus.info.data.database.CharacterEntity
import com.shub39.plumbus.info.data.database.EpisodeEntity
import com.shub39.plumbus.info.data.database.LocationEntity
import com.shub39.plumbus.info.data.dto.character.CharacterDto
import com.shub39.plumbus.info.data.dto.episode.EpisodeDto
import com.shub39.plumbus.info.data.dto.location.LocationDto
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.domain.Location

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        type = type,
        species = species,
        gender = gender,
        imageUrl = image,
        episodes = episode,
        url = url,
        origin = Pair(origin.name, origin.url),
        location = Pair(location.name, location.url)
    )
}

fun Character.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = imageUrl,
        episode = episodes,
        url = url,
        origin = Pair(origin.first, origin.second),
        location = Pair(location.first, location.second),
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        type = type,
        species = species,
        gender = gender,
        imageUrl = image,
        episodes = episode,
        url = url,
        origin = origin,
        location = location
    )
}

fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        url = url,
        type = type,
        dimension = dimension,
        residents = residents,
    )
}

fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        url = url,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}

fun LocationEntity.toLocation(): Location {
    return Location(
        url = url,
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters,
        url = url
    )
}

fun Episode.toEpisodeEntity(): EpisodeEntity{
    return EpisodeEntity(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters,
        url = url
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters,
        url = url
    )
}