package com.shub39.plumbus.info.data.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val origin: Origin,
    val location: Location,
    val created: String
)
