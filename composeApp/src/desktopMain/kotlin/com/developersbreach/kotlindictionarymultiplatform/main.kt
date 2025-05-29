@file:Suppress("ktlint")

package com.developersbreach.kotlindictionarymultiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Kotlin Dictionary Multiplatform",
    ) {
        App()
    }
}