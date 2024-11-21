package com.shub39.plumbus.info.data.dto.episode

import com.shub39.plumbus.info.data.dto.Info
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val info: Info,
    val results: List<EpisodeDto>
)
