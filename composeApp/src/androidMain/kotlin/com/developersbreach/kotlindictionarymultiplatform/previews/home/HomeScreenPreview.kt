package com.developersbreach.kotlindictionarymultiplatform.previews.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.sampleTopicUiList
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.home.HomeScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    KotlinDictionaryTheme {
        HomeScreenUI(
            topics = sampleTopicUiList(),
            onViewAllClick = {},
        )
    }
}