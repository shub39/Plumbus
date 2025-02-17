package com.shub39.plumbus.info.data.dto.character

import com.shub39.plumbus.info.data.dto.Info
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDto>
)
