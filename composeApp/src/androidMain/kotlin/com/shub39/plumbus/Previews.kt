package com.shub39.plumbus

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.presentation.character_list.component.CLItem

import com.shub39.plumbus.info.presentation.components.GeneralSearchBar

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SearchBarPreview() {
    GeneralSearchBar(
        searchQuery = "Kotlin",
        onSearchQueryChange = {},
        onImeAction = {},
        modifier = Modifier.fillMaxWidth()
    )

    CLItem(
        character = Character(
            id = 0,
            name = "Rick",
            status = "Alive",
            type = "Human",
            species = "Human",
            gender = "Male",
            imageUrl = "",
            episodes = emptyList(),
            url = "",
            origin = Pair("", ""),
            location = Pair("", "")
        ),
        onClick = {}
    )
}