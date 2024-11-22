package com.shub39.plumbus.info.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.domain.Location
import com.shub39.plumbus.info.presentation.character.CLState
import com.shub39.plumbus.info.presentation.character.component.CharacterList
import com.shub39.plumbus.info.presentation.components.NoFavs
import com.shub39.plumbus.info.presentation.episode.ELState
import com.shub39.plumbus.info.presentation.episode.components.EpisodeList
import com.shub39.plumbus.info.presentation.location.LLState
import com.shub39.plumbus.info.presentation.location.component.LocationsList
import org.jetbrains.compose.resources.stringResource
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.favorites

@Composable
fun HomePage(
    clState: CLState,
    elState: ELState,
    llState: LLState,
    onCharacterClick: (Character) -> Unit,
    onEpisodeClick: (Episode) -> Unit,
    onLocationClick: (Location) -> Unit
) {
    val options = listOf(
        "Character",
        "Episode",
        "Location"
    )

    var selectedItem by remember { mutableStateOf(options[0]) }
    val listState = rememberLazyListState()

    LaunchedEffect(selectedItem) {
        listState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .widthIn(max = 700.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.favorites),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEach { option ->
                    val selected = option == selectedItem

                    val contentColor by animateColorAsState(
                        targetValue = when(selected) {
                            true -> MaterialTheme.colorScheme.surface
                            false -> MaterialTheme.colorScheme.primary
                        }
                    )
                    val containerColor by animateColorAsState(
                        targetValue = when(selected) {
                            true -> MaterialTheme.colorScheme.primary
                            false -> MaterialTheme.colorScheme.surface
                        }
                    )

                    IconButton(
                        onClick = { selectedItem = option },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = contentColor,
                            containerColor = containerColor
                        )
                    ) {
                        Icon(
                            imageVector = when (option) {
                                options[0] -> Icons.Default.Person
                                options[1] -> Icons.Default.Info
                                options[2] -> Icons.Default.LocationOn
                                else -> Icons.Default.Person
                            },
                            contentDescription = null
                        )
                    }
                }
            }
        }

        AnimatedContent(
            targetState = selectedItem
        ) {
            when (it) {
                options[0] -> {
                    if (clState.favs.isEmpty()) {
                        NoFavs()
                    } else {
                        CharacterList(
                            characters = clState.favs,
                            onCharacterClick = onCharacterClick,
                            onCharacterFav = {},
                            favAvailable = false,
                            lazyListState = listState,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                options[1] -> {
                    if (elState.favs.isEmpty()) {
                        NoFavs()
                    } else {
                        EpisodeList(
                            episodes = elState.favs,
                            onEpisodeClick = onEpisodeClick,
                            onEpisodeFav = {},
                            favAvailable = false,
                            lazyListState = listState,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                options[2] -> {
                    if (llState.favs.isEmpty()) {
                        NoFavs()
                    } else {
                        LocationsList(
                            locations = llState.favs,
                            onLocationClick = onLocationClick,
                            onLocationFav = {},
                            favAvailable = false,
                            lazyListState = listState,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}