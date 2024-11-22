package com.shub39.plumbus.app

import kotlinx.serialization.Serializable

// the possible routes
sealed interface Route {
    @Serializable
    data object PlumbusGraph: Route

    @Serializable
    data object HomePage: Route

    @Serializable
    data object CharacterList: Route

    @Serializable
    data object CharacterDetail: Route

    @Serializable
    data object EpisodeList: Route

    @Serializable
    data object EpisodeDetail: Route

    @Serializable
    data object LocationList: Route

    @Serializable
    data object LocationDetail: Route
}