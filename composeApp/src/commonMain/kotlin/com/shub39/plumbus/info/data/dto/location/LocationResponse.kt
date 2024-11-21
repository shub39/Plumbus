package com.shub39.plumbus.info.data.dto.location

import com.shub39.plumbus.info.data.dto.Info
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val info: Info,
    val results: List<LocationDto>
)
