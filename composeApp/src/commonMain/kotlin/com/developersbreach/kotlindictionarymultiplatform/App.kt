package com.developersbreach.kotlindictionarymultiplatform

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppNavigation

@Composable
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}