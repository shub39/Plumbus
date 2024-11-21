package com.shub39.plumbus.info.presentation.location_list

import com.shub39.plumbus.info.domain.Location

// all location actions
sealed interface LLAction {
    data class OnSearchQueryChange(val query: String): LLAction
    data class OnLocationClick(val location: Location): LLAction
    data class OnTabSelected(val index: Int): LLAction
}