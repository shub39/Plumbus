package com.shub39.plumbus.info.presentation.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.info.presentation.character.component.CharacterList
import com.shub39.plumbus.info.presentation.components.GeneralSearchBar
import org.jetbrains.compose.resources.stringResource
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.no_results
import plumbus.composeapp.generated.resources.no_saved
import plumbus.composeapp.generated.resources.saved
import plumbus.composeapp.generated.resources.search_results

// character list screen
@Composable
fun CLScreen(
    state: CLState,
    onAction: (CLAction) -> Unit,
    onNavigate: () -> Unit
) {
    val pagerState = rememberPagerState { 2 }
    val searchListState = rememberLazyListState()
    val savedListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(state.searchResults) {
        searchListState.animateScrollToItem(0)
    }

    LaunchedEffect(state.selectIndex) {
        pagerState.animateScrollToPage(state.selectIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        onAction(CLAction.OnTabSelected(pagerState.currentPage))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
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

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabRow(
                    selectedTabIndex = state.selectIndex,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .widthIn(max = 700.dp)
                        .fillMaxWidth()
                ) {
                    Tab(
                        selected = state.selectIndex == 0,
                        onClick = { onAction(CLAction.OnTabSelected(0)) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }

                    Tab(
                        selected = state.selectIndex == 1,
                        onClick = { onAction(CLAction.OnTabSelected(1)) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(Res.string.saved),
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        when (page) {
                            0 -> {
                                if (state.isLoading) {
                                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                                } else {
                                    when {
                                        state.errorMessage != null -> {
                                            Text(
                                                text = state.errorMessage.asString(),
                                                textAlign = TextAlign.Center,
                                                color = MaterialTheme.colorScheme.error,
                                                style = MaterialTheme.typography.headlineSmall,
                                                modifier = Modifier.align(Alignment.Center)
                                            )
                                        }

                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                modifier = Modifier.align(Alignment.Center)
                                            )
                                        }

                                        else -> {
                                            CharacterList(
                                                characters = state.searchResults,
                                                onCharacterClick = {
                                                    onAction(CLAction.OnCharacterClick(it))
                                                    onNavigate()
                                                },
                                                onCharacterFav = {
                                                    onAction(CLAction.OnSetFav(it.id))
                                                },
                                                favAvailable = false,
                                                modifier = Modifier.fillMaxSize(),
                                                lazyListState = searchListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if (state.saved.isEmpty()) {
                                    Text(
                                        text = stringResource(Res.string.no_saved),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                } else {
                                    CharacterList(
                                        characters = state.saved,
                                        onCharacterClick = {
                                            onAction(CLAction.OnCharacterClick(it))
                                            onNavigate()
                                        },
                                        onCharacterFav = {
                                            onAction(CLAction.OnSetFav(it.id))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        lazyListState = savedListState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}