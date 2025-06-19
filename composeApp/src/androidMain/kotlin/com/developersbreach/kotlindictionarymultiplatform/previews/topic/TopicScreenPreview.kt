package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.sampleTopicUiList
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
private fun TopicScreenPreview() {
    KotlinDictionaryTheme {
        TopicScreenUI(
            topics = sampleTopicUiList(),
            searchQuery = "Search",
            onQueryChange = { },
            onTopicClick = { },
        )
    }
}