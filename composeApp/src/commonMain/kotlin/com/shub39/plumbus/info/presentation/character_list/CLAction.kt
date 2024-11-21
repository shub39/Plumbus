package com.shub39.plumbus.info.presentation.character_list

import com.shub39.plumbus.info.domain.Character

// All user actions in book list
sealed interface CLAction {
    data class OnSearchQueryChange(val query: String): CLAction
    data class OnCharacterClick(val character: Character): CLAction
    data class OnTabSelected(val index: Int): CLAction
}