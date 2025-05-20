package com.developersbreach.kotlindictionarymultiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.developersbreach.kotlindictionarymultiplatform.di.appModule
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppNavigation
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreen
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Kotlin Dictionary Multiplatform",
        ) {
            AppNavigation()
        }
    }
}