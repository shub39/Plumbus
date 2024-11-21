package com.shub39.plumbus.info.presentation.location_list

import com.shub39.plumbus.core.presentation.UiText
import com.shub39.plumbus.info.domain.Location

// location list state
data class LLState (
    val searchQuery: String = "",
    val searchResults: List<Location> = emptyList(),
    val saved: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val selectIndex: Int = 0,
    val errorMessage: UiText? = null
)