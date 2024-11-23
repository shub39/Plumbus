package com.shub39.plumbus.info.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.domain.Character
import com.shub39.plumbus.info.domain.CharacterRepo
import com.shub39.plumbus.info.presentation.character.component.CLItem
import com.shub39.plumbus.info.presentation.location.LLAction
import com.shub39.plumbus.info.presentation.location.LLState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.characters
import plumbus.composeapp.generated.resources.not_found

@Composable
fun LocationScreen(
    state: LLState,
    action: (LLAction) -> Unit,
    onCharacterClick: (Character) -> Unit,
    onBack: () -> Unit,
    characterDataSource: CharacterRepo = koinInject()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = null
            )
        }

        if (state.currentLocation == null) {

            CircularProgressIndicator()

        } else {
            val location = state.currentLocation
            var isFav by remember { mutableStateOf(location.isFav) }

            var characters: List<Result<Character, DataError.Remote>>? by remember { mutableStateOf(null) }

            LaunchedEffect(Unit) {
                characters = location.residents.map {
                    characterDataSource.getSingleCharacter(it)
                }
            }

            IconButton(
                onClick = {
                    action(LLAction.OnSetFav(location.id))
                    isFav = !isFav
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopEnd)
            ) {
                if (isFav) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = location.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Text(
                        text = location.dimension,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = location.type,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (location.residents.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.padding(16.dp))

                        Text(
                            text = stringResource(Res.string.characters),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        if (characters == null) {

                            CircularProgressIndicator(
                                modifier = Modifier.padding(32.dp)
                            )

                        } else {
                            characters!!.forEach {
                                if (it is Result.Success) {
                                    CLItem(
                                        character = it.data,
                                        onClick = { onCharacterClick(it.data) },
                                        onFav = {},
                                        favAvailable = false
                                    )

                                    Spacer(modifier = Modifier.padding(8.dp))
                                } else {
                                    Text(
                                        text = stringResource(Res.string.not_found),
                                        style = MaterialTheme.typography.bodyMedium,
                                        textAlign = TextAlign.Center
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