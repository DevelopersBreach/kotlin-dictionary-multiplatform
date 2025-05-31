package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.SearchField
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@Preview(name = "SearchField - Light", showBackground = true)
@Composable
fun SearchFieldLightPreview() {
    KotlinDictionaryTheme(useDarkTheme = false) {
        var searchQuery = remember { mutableStateOf("") }
        SearchField(searchQuery = searchQuery)
    }
}

@Preview(name = "SearchField - Dark", showBackground = true)
@Composable
fun SearchFieldDarkPreview() {
    KotlinDictionaryTheme(useDarkTheme = true) {
        var searchQuery = remember { mutableStateOf("") }
        SearchField(searchQuery = searchQuery)
    }
}