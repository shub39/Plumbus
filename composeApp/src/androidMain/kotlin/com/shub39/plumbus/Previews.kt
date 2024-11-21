package com.shub39.plumbus

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.presentation.character_list.CLScreen
import com.shub39.plumbus.info.presentation.character_list.CLState
import com.shub39.plumbus.info.presentation.episode_list.ELScreen
import com.shub39.plumbus.info.presentation.episode_list.ELState

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun CLScreenPreview() {

    CLScreen(
        state = CLState(
            searchResults = (1..100).map {
                Character(
                    id = it,
                    name = "Character $it",
                    status = if (it % 2 == 0) "Alive" else "Dead",
                    type = "type $it",
                    species = "Whatever",
                    gender = if (it % 2 == 0) "Male" else "Female",
                    imageUrl = "",
                    episodes = emptyList(),
                    url = "",
                    origin = Pair("",""),
                    location = Pair("", ""),
                )
            },
        ),
        onAction = {  },
        onNavigate = {  }
    ) 

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun ELScreenPreview() {

    ELScreen(
        state = ELState(
            searchResults = (0..100).map { 
                Episode(
                    id = it,
                    name = "episode $it",
                    airDate = "Random Date",
                    episode = "",
                    characters = emptyList(),
                    url = ""
                )
            }
        ),
        action = {},
        onNavigate = {}
    )

}