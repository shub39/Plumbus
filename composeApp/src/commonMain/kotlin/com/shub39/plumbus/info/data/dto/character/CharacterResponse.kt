package com.shub39.plumbus.info.data.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDto>
)
