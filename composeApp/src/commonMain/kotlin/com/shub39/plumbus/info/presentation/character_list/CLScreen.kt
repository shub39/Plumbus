package com.shub39.plumbus.info.presentation.character_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.info.presentation.components.GeneralSearchBar

// character list screen
@Composable
fun CLScreen(
    state: CLState,
    onAction: (CLAction) -> Unit,
    onNavigate: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GeneralSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(CLAction.OnSearchQueryChange(it))
            },
            onImeAction = {
                keyboardController?.hide()
            },
            modifier = Modifier
                .widthIn(max = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}