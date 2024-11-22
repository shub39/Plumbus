package com.shub39.plumbus.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.stringResource
import plumbus.composeapp.generated.resources.Res
import plumbus.composeapp.generated.resources.character
import plumbus.composeapp.generated.resources.episodes
import plumbus.composeapp.generated.resources.home
import plumbus.composeapp.generated.resources.locations

// the bottom bar, improvised selected in navbaritem
@Composable
fun BottomBar(
    navController: NavHostController,
) {
    var currentRoute: Route by remember { mutableStateOf(Route.HomePage) }

    val routes = listOf(
        Route.HomePage,
        Route.CharacterList,
        Route.EpisodeList,
        Route.LocationList
    )

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        NavigationBar(
            modifier = Modifier
                .widthIn(max = 700.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        ) {
            routes.forEach { route ->
                NavigationBarItem(
                    selected = currentRoute == route,
                    onClick = {
                        navController.navigate(route) {
                            launchSingleTop = true
                        }
                        currentRoute = route
                    },
                    icon = {
                        Icon(
                            imageVector = when (route) {
                                Route.HomePage -> { Icons.Filled.Home }
                                Route.CharacterList -> { Icons.Filled.Person }
                                Route.EpisodeList -> { Icons.Filled.Info }
                                Route.LocationList -> { Icons.Filled.LocationOn }
                                else -> { Icons.Filled.Person }
                            },
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(
                            text = when (route) {
                                Route.HomePage -> stringResource(Res.string.home)
                                Route.CharacterList -> stringResource(Res.string.character)
                                Route.EpisodeList -> stringResource(Res.string.episodes)
                                Route.LocationList -> stringResource(Res.string.locations)
                                else -> stringResource(Res.string.character)
                            }
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}