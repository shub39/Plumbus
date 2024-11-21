package com.shub39.plumbus.app

import kotlinx.serialization.Serializable

// the possible routes
sealed interface Route {
    @Serializable
    data object PlumbusGraph: Route

    @Serializable
    data object CharacterList: Route

    @Serializable
    data class CharacterDetail(val id: Int): Route

    @Serializable
    data object EpisodeList: Route

    @Serializable
    data class EpisodeDetail(val id: Int): Route

    @Serializable
    data object LocationList: Route

    @Serializable
    data class LocationDetail(val id: Int): Route
}