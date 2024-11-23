package com.shub39.plumbus.info.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shub39.plumbus.core.domain.DataError
import com.shub39.plumbus.core.domain.Result
import com.shub39.plumbus.info.domain.Episode
import com.shub39.plumbus.info.domain.EpisodeRepo
import com.shub39.plumbus.info.domain.Location
import com.shub39.plumbus.info.domain.LocationRepo
import com.shub39.plumbus.info.presentation.character.CLAction
import com.shub39.plumbus.info.presentation.character.CLState
import com.shub39.plumbus.info.presentation.episode.components.ELItem
import com.shub39.plumbus.info.presentation.location.component.LLItem
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerContainer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.episodes
import plumbus.composeapp.generated.resources.last_location
import plumbus.composeapp.generated.resources.not_found
import plumbus.composeapp.generated.resources.origin

@Composable
fun CharacterScreen(
    state: CLState,
    action: (CLAction) -> Unit,
    onEpisodeClick: (Episode) -> Unit,
    onLocationClick: (Location) -> Unit,
    onBack: () -> Unit,
    locationDataSource: LocationRepo = koinInject(),
    episodeDataSource: EpisodeRepo = koinInject()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // back button, navcontroller.navigateUp() works as intended on android
        // but not on desktop, maybe bug?
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

        if (state.currentCharacter == null) {

            // because impossible case, idk what else to show ☠
            CircularProgressIndicator()

        } else {
            val character = state.currentCharacter
            var isFav by remember { mutableStateOf(character.isFav) }

            var origin: Result<Location, DataError.Remote>? by remember { mutableStateOf(null) }
            var lastLocation: Result<Location, DataError.Remote>? by remember { mutableStateOf(null) }
            var episodes: List<Result<Episode, DataError.Remote>>? by remember { mutableStateOf(null) }

            LaunchedEffect(Unit) {
                origin = character.origin.second.let {
                    if (it.isNotBlank()) {
                        locationDataSource.getSingleLocation(it)
                    } else {
                        Result.Error(DataError.Remote.NO_RESULTS)
                    }
                }
                lastLocation = character.location.second.let {
                    if (it.isNotBlank()) {
                        locationDataSource.getSingleLocation(it)
                    } else {
                        Result.Error(DataError.Remote.NO_RESULTS)
                    }
                }
                episodes = character.episodes.map {
                    episodeDataSource.getSingleEpisode(it)
                }
            }

            // favorite button
            IconButton(
                onClick = {
                    action(CLAction.OnSetFav(character.id))
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

            // background image
            CoilImage(
                imageModel = { character.imageUrl },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillWidth,
                    alignment = Alignment.Center,
                    alpha = 0.5f
                ),
                loading = {
                    ShimmerContainer(
                        modifier = Modifier.align(Alignment.Center),
                        shimmer = Shimmer.Flash(
                            baseColor = MaterialTheme.colorScheme.surface,
                            highlightColor = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .height(300.dp)
                    .fillMaxWidth()
            )

            // the sweep over background image 💀
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    )
            )

            // the main content
            LazyColumn(
                modifier = Modifier
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                    .widthIn(max = 700.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    CoilImage(
                        imageModel = { character.imageUrl },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        ),
                        failure = {
                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                            )
                        },
                        loading = {
                            ShimmerContainer(
                                modifier = Modifier.align(Alignment.Center),
                                shimmer = Shimmer.Flash(
                                    baseColor = MaterialTheme.colorScheme.surface,
                                    highlightColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        },
                        modifier = Modifier
                            .size(200.dp)
                            .aspectRatio(ratio = 1f)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(8.dp))

                    Text(
                        text = character.name,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Text(
                        text = "${character.species} - ${character.gender}",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis
                    )

                    if (character.type.isNotBlank()) {
                        Text(
                            text = character.type,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Text(
                        text = character.status,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        overflow = TextOverflow.Ellipsis,
                        color = when (character.status.lowercase()) {
                            "dead" -> MaterialTheme.colorScheme.error
                            "alive" -> MaterialTheme.colorScheme.primary
                            else -> Color.Gray
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.padding(16.dp))

                    Text(
                        text = stringResource(Res.string.origin),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    if (origin == null) {

                        CircularProgressIndicator(
                            modifier = Modifier.padding(32.dp)
                        )

                    } else {
                        if (origin is Result.Error) {
                            Text(
                                text = stringResource(Res.string.not_found),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            val success = (origin as Result.Success).data

                            LLItem(
                                location = success,
                                onClick = { onLocationClick(success) },
                                onFav = {},
                                favAvailable = false
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.padding(16.dp))

                    Text(
                        text = stringResource(Res.string.last_location),
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.padding(8.dp))

                    if (lastLocation == null) {

                        CircularProgressIndicator(
                            modifier = Modifier.padding(32.dp)
                        )

                    } else {
                        if (lastLocation is Result.Error) {
                            Text(
                                text = stringResource(Res.string.not_found),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            val success = (lastLocation as Result.Success).data

                            LLItem(
                                location = success,
                                onClick = { onLocationClick(success) },
                                onFav = { },
                                favAvailable = false
                            )
                        }
                    }
                }

                if (character.episodes.isNotEmpty()) {
                    item {
                        if (episodes == null) {

                            CircularProgressIndicator(
                                modifier = Modifier.padding(32.dp)
                            )

                        } else {
                            Spacer(modifier = Modifier.padding(16.dp))

                            Text(
                                text = stringResource(Res.string.episodes),
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.padding(8.dp))

                            episodes!!.forEach {
                                if (it is Result.Success) {
                                    ELItem(
                                        episode = it.data,
                                        onClick = { onEpisodeClick(it.data) },
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

                item {
                    Spacer(modifier = Modifier.padding(60.dp))
                }
            }
        }
    }
}