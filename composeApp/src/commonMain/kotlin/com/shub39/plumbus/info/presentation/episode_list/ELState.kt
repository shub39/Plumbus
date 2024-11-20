package com.shub39.plumbus.info.presentation.episode_list

import com.shub39.plumbus.core.presentation.UiText
import com.shub39.plumbus.info.domain.Episode

// episode list screen
data class ELState (
    val searchQuery: String = "",
    val searchResults: List<Episode> = emptyList(),
    val favorites: List<Episode> = emptyList(),
    val isLoading: Boolean = false,
    val selectIndex: Int = 0,
    val errorMessage: UiText? = null
)