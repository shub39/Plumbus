package com.shub39.plumbus.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shub39.plumbus.core.presentation.PlumbusTheme
import com.shub39.plumbus.info.presentation.HomePage
import com.shub39.plumbus.info.presentation.character_list.CLScreen
import com.shub39.plumbus.info.presentation.character_list.CLViewModel
import com.shub39.plumbus.info.presentation.episode_list.ELScreen
import com.shub39.plumbus.info.presentation.episode_list.ELViewModel
import com.shub39.plumbus.info.presentation.location_list.LLScreen
import com.shub39.plumbus.info.presentation.location_list.LLViewModel
import org.koin.compose.viewmodel.koinViewModel

// the homepage
@Composable
fun App(
    clvm: CLViewModel = koinViewModel(),
    elvm: ELViewModel = koinViewModel(),
    llvm: LLViewModel = koinViewModel()
) {
    PlumbusTheme {
        val navController = rememberNavController()

        val clState by clvm.state.collectAsStateWithLifecycle()
        val elState by elvm.state.collectAsStateWithLifecycle()
        val llState by llvm.state.collectAsStateWithLifecycle()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomBar(navController) }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.PlumbusGraph,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                navigation<Route.PlumbusGraph>(
                    startDestination = Route.HomePage
                ) {
                    composable<Route.HomePage> {
                        HomePage(
                            clState = clState,
                            elState = elState,
                            llState = llState,
                            onCharacterClick = { character ->
                                navController.navigate(Route.CharacterDetail(character.id)) {
                                    launchSingleTop = true
                                }
                            },
                            onEpisodeClick = { episode ->
                                navController.navigate(Route.EpisodeDetail(episode.id)) {
                                    launchSingleTop = true
                                }
                            },
                            onLocationClick = { location ->
                                navController.navigate(Route.LocationDetail(location.id)) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable<Route.CharacterList> {
                        CLScreen(
                            state = clState,
                            onAction = clvm::action,
                            onNavigate = { character ->
                                navController.navigate(Route.CharacterDetail(character.id)) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable<Route.EpisodeList> {
                        ELScreen(
                            state = elState,
                            action = elvm::action,
                            onNavigate = { episode ->
                                navController.navigate(Route.EpisodeDetail(episode.id)) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable<Route.LocationList> {
                        LLScreen(
                            state = llState,
                            action = llvm::action,
                            onNavigate = { location ->
                                navController.navigate(Route.LocationDetail(location.id)) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable<Route.CharacterDetail> { entry ->
                        val args = entry.toRoute<Route.CharacterDetail>()

                        Text("Character Detail ${args.id}")
                    }

                    composable<Route.EpisodeDetail> { entry ->
                        val args = entry.toRoute<Route.EpisodeDetail>()

                        Text("Episode Detail ${args.id}")
                    }

                    composable<Route.LocationDetail> { entry ->
                        val args = entry.toRoute<Route.LocationDetail>()

                        Text("Location Detail ${args.id}")
                    }
                }
            }
        }
    }
}