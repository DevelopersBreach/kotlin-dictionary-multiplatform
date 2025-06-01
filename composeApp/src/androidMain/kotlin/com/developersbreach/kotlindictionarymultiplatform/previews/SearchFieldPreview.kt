package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.SearchField
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun SearchFieldLightPreview() {
    KotlinDictionaryTheme {
        val searchQuery = remember { mutableStateOf("") }
        SearchField(searchQuery = searchQuery)
    }
}
