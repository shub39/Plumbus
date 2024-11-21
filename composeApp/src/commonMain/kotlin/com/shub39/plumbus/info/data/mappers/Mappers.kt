package com.shub39.plumbus.info.data.mappers

import com.shub39.plumbus.info.data.dto.character.CharacterDto
import com.shub39.plumbus.info.domain.character.Character

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