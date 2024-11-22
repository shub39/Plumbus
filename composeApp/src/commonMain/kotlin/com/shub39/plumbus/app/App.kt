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
import com.shub39.plumbus.core.presentation.PlumbusTheme
import com.shub39.plumbus.info.presentation.CharacterScreen
import com.shub39.plumbus.info.presentation.HomePage
import com.shub39.plumbus.info.presentation.character.CLAction
import com.shub39.plumbus.info.presentation.character.CLScreen
import com.shub39.plumbus.info.presentation.character.CLViewModel
import com.shub39.plumbus.info.presentation.episode.ELAction
import com.shub39.plumbus.info.presentation.episode.ELScreen
import com.shub39.plumbus.info.presentation.episode.ELViewModel
import com.shub39.plumbus.info.presentation.location.LLAction
import com.shub39.plumbus.info.presentation.location.LLScreen
import com.shub39.plumbus.info.presentation.location.LLViewModel
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
        var currentRoute: Route by remember { mutableStateOf(Route.HomePage) }

        val routes = listOf(
            Route.HomePage,
            Route.CharacterList,
            Route.EpisodeList,
            Route.LocationList
        )

        val clState by clvm.state.collectAsStateWithLifecycle()
        val elState by elvm.state.collectAsStateWithLifecycle()
        val llState by llvm.state.collectAsStateWithLifecycle()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomBar(
                    navController = navController,
                    routes = routes,
                    currentRoute = currentRoute
                )
            }
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
                        currentRoute = Route.HomePage

                        HomePage(
                            clState = clState,
                            elState = elState,
                            llState = llState,
                            onCharacterClick = { character ->
                                clvm.action(CLAction.OnCharacterClick(character))

                                navController.navigate(Route.CharacterDetail) {
                                    launchSingleTop = true
                                }
                            },
                            onEpisodeClick = { episode ->
                                elvm.action(ELAction.OnEpisodeClick(episode))

//                                navController.navigate(Route.EpisodeDetail) {
//                                    launchSingleTop = true
//                                }
                            },
                            onLocationClick = { location ->
                                llvm.action(LLAction.OnLocationClick(location))

//                                navController.navigate(Route.LocationDetail) {
//                                    launchSingleTop = true
//                                }
                            }
                        )
                    }

                    composable<Route.CharacterList> {
                        currentRoute = Route.CharacterList

                        CLScreen(
                            state = clState,
                            onAction = clvm::action,
                            onNavigate = {
                                navController.navigate(Route.CharacterDetail) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable<Route.EpisodeList> {
                        currentRoute = Route.EpisodeList

                        ELScreen(
                            state = elState,
                            action = elvm::action,
                            onNavigate = {
//                                navController.navigate(Route.EpisodeDetail) {
//                                    launchSingleTop = true
//                                }
                            }
                        )
                    }

                    composable<Route.LocationList> {
                        currentRoute = Route.LocationList

                        LLScreen(
                            state = llState,
                            action = llvm::action,
                            onNavigate = {
//                                navController.navigate(Route.LocationDetail) {
//                                    launchSingleTop = true
//                                }
                            }
                        )
                    }

                    composable<Route.CharacterDetail> {
                        CharacterScreen(
                            state = clState,
                            action = clvm::action,
                            onBack = { navController.navigateUp() },
                            onEpisodeClick = {
                                elvm.action(ELAction.OnEpisodeClick(it))
//                                navController.navigate(Route.EpisodeDetail)
                            },
                            onLocationClick = {
                                llvm.action(LLAction.OnLocationClick(it))
//                                navController.navigate(Route.LocationDetail)
                            }
                        )
                    }

                    composable<Route.EpisodeDetail> {
                        Text("Episode Detail")
                    }

                    composable<Route.LocationDetail> {
                        Text("Location Detail")
                    }
                }
            }
        }
    }
}