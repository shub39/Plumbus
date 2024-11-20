package com.shub39.plumbus.info.data.dto.character

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String,
    val url: String
)
