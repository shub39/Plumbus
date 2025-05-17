package com.shub39.plumbus

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.shub39.plumbus.app.App
import com.shub39.plumbus.di.initKoin

fun main() {
    initKoin()

    singleWindowApplication(
        state = WindowState(size = DpSize(width = 600.dp, height = 900.dp)),
        title = "Plumbus"
    ) {
        App()
    }
}