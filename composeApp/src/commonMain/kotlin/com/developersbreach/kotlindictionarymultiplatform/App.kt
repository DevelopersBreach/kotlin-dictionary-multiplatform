package com.developersbreach.kotlindictionarymultiplatform

import androidx.compose.runtime.Composable
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppNavigation
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@Composable
fun App() {
    KotlinDictionaryTheme {
        AppNavigation()
    }
}