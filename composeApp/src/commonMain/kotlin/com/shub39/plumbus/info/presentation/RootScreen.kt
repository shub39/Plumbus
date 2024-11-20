package com.shub39.plumbus.info.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shub39.plumbus.info.presentation.character_list.CLScreen
import com.shub39.plumbus.info.presentation.character_list.CLViewModel
import com.shub39.plumbus.info.presentation.episode_list.ELViewModel
import com.shub39.plumbus.info.presentation.location_list.LLViewModel
import org.koin.compose.viewmodel.koinViewModel

// the Homepage of sorts
@Composable
fun RootScreen(
    clvm: CLViewModel = koinViewModel(),
    elvm: ELViewModel = koinViewModel(),
    llvm: LLViewModel = koinViewModel()
) {
    val clState by clvm.state.collectAsStateWithLifecycle()

    Scaffold (

    ) {
        CLScreen(
            state = clState,
            onAction = clvm::action,
            onNavigate = {}
        )
    }
}