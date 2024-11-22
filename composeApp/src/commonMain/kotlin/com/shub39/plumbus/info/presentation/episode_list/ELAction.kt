package com.shub39.plumbus.info.presentation.episode_list

import com.shub39.plumbus.info.domain.Episode

// all episode list actions
sealed interface ELAction {
    data class OnSearchQueryChange(val query: String): ELAction
    data class OnEpisodeClick(val episode: Episode): ELAction
    data class OnTabSelected(val index: Int): ELAction
    data class OnSetFav(val id: Int): ELAction
}