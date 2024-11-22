package com.shub39.plumbus

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.domain.Location
import com.shub39.plumbus.info.presentation.HomePage
import com.shub39.plumbus.info.presentation.character.CLScreen
import com.shub39.plumbus.info.presentation.character.CLState
import com.shub39.plumbus.info.presentation.episode.ELScreen
import com.shub39.plumbus.info.presentation.episode.ELState
import com.shub39.plumbus.info.presentation.location.LLState

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
                    isFav = it % 3 == 0
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
                    url = "",
                    isFav = it % 3 == 0
                )
            }
        ),
        action = {},
        onNavigate = {}
    )

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun HomePagePreview() {
    HomePage(
        clState = CLState(
            favs = (1..10).map {
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
                    isFav = true
                )
            }
        ),
        elState = ELState(
            favs = (1..10).map {
                Episode(
                    id = it,
                    name = "episode $it",
                    airDate = "Random Date",
                    episode = "",
                    characters = emptyList(),
                    url = "",
                    isFav = true
                )
            }
        ),
        llState = LLState(
            favs = (1..10).map { 
                Location(
                    url = "",
                    id = it,
                    name = "Location no: $it",
                    type = "Location Type: $it",
                    dimension = "Dimension $it",
                    residents = emptyList(),
                    isFav = true
                )
            }
        ),
        onCharacterClick = {  },
        onEpisodeClick = {  },
        onLocationClick = {  }
    )
}