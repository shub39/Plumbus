package com.shub39.plumbus

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.shub39.plumbus.app.App
import com.shub39.plumbus.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Plumbus",
            resizable = false,
            state = WindowState(
                size = DpSize(width = 800.dp, height = 900.dp)
            )
        ) {
            App()
        }
    }
}