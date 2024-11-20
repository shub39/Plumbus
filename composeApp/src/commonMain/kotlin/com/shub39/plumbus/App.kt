package com.shub39.plumbus

import androidx.compose.runtime.*
import com.shub39.plumbus.core.presentation.PlumbusTheme
import com.shub39.plumbus.info.presentation.RootScreen
import com.shub39.plumbus.info.presentation.character_list.CLViewModel
import com.shub39.plumbus.info.presentation.episode_list.ELViewModel
import com.shub39.plumbus.info.presentation.location_list.LLViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    PlumbusTheme {
        RootScreen(
            clvm = remember { CLViewModel() },
            elvm = remember { ELViewModel() },
            llvm = remember { LLViewModel() }
        )
    }
}