package com.shub39.plumbus.info.presentation.character

import com.shub39.plumbus.core.presentation.UiText
import com.shub39.plumbus.info.domain.Character

// state for character list
data class CLState(
    val searchQuery: String = "",
    val currentCharacter: Character? = null,
    val searchResults: List<Character> = emptyList(),
    val saved: List<Character> = emptyList(),
    val favs: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val selectIndex: Int = 0,
    val errorMessage: UiText? = null
)
