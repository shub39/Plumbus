package com.shub39.plumbus

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.shub39.plumbus.app.App
import com.shub39.plumbus.di.initKoin

fun main() {
    initKoin()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Plumbus",
        ) {
            App()
        }
    }
}