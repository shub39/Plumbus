package com.shub39.plumbus.info.presentation.character.component

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
import com.shub39.plumbus.info.domain.Character

// characters list
@Composable
fun CharacterList(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit,
    onCharacterFav: (Character) -> Unit,
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
        items(characters, key = { it.id }) { character ->
            CLItem(
                character = character,
                onClick = { onCharacterClick(character) },
                onFav = { onCharacterFav(character) },
                favAvailable = favAvailable
            )
        }

        item {
            Spacer(modifier.padding(vertical = 60.dp))
        }
    }
}