package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.SearchField
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun SearchFieldPreview() {
    KotlinDictionaryTheme {
        var searchQuery by remember { mutableStateOf("") }
        SearchField(
            searchQuery = searchQuery,
            onQueryChange = { searchQuery = it },
        )
    }
}