package com.shub39.plumbus.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shub39.plumbus.core.presentation.PlumbusTheme
import com.shub39.plumbus.info.presentation.character_list.CLScreen
import com.shub39.plumbus.info.presentation.character_list.CLViewModel
import com.shub39.plumbus.info.presentation.episode_list.ELViewModel
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

        val clState by clvm.state.collectAsState()
        val elState by elvm.state.collectAsState()
        val llState by llvm.state.collectAsState()

        Scaffold (
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
                    startDestination = Route.CharacterList
                ) {
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
                        Text("Episode List")
                    }

                    composable<Route.LocationList> {
                        Text("Location List")
                    }

                    composable<Route.CharacterDetail> { entry ->
                        val args = entry.toRoute<Route.CharacterDetail>()

                        Text("Character Detail ${args.id}")
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