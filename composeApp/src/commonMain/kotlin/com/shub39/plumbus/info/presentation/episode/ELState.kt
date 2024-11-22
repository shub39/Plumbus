package com.shub39.plumbus.info.presentation.episode

import com.shub39.plumbus.core.presentation.UiText
import com.shub39.plumbus.info.domain.Episode

// episode list screen
data class ELState (
    val searchQuery: String = "",
    val currentEpisode: Episode? = null,
    val searchResults: List<Episode> = emptyList(),
    val saved: List<Episode> = emptyList(),
    val favs: List<Episode> = emptyList(),
    val isLoading: Boolean = false,
    val selectIndex: Int = 0,
    val errorMessage: UiText? = null
)