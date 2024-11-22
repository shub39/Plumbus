package com.shub39.plumbus.info.presentation.episode.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.info.domain.Episode

@Composable
fun EpisodeList(
    episodes: List<Episode>,
    onEpisodeClick: (Episode) -> Unit,
    onEpisodeFav: (Episode) -> Unit,
    favAvailable: Boolean = true,
    lazyListState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(episodes, key = { it.id }) { episode ->
            ELItem(
                episode = episode,
                onClick = { onEpisodeClick(episode) },
                onFav = { onEpisodeFav(episode) },
                favAvailable = favAvailable
            )
        }

        item {
            Spacer(modifier.padding(vertical = 60.dp))
        }
    }
}