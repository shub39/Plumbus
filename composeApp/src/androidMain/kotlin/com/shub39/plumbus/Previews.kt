package com.shub39.plumbus

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.presentation.character_list.CLScreen
import com.shub39.plumbus.info.presentation.character_list.CLState

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun Preview() {

    CLScreen(
        state = CLState(
            searchResults = (1L..100L).map {
                Character(
                    id = it,
                    name = "Character $it",
                    status = if (it % 2L == 0L) "Alive" else "Dead",
                    type = "type $it",
                    species = "Whatever",
                    gender = if (it % 2L == 0L) "Male" else "Female",
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