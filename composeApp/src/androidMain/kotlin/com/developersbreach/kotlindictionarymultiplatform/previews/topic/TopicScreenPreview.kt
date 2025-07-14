package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import app.cash.paging.compose.collectAsLazyPagingItems
import com.developersbreach.kotlindictionarymultiplatform.previews.samplePagingData
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
private fun TopicScreenPreview() {
    KotlinDictionaryTheme {
        val pagingItems = samplePagingData().collectAsLazyPagingItems()
        TopicScreenUI(
            topics = pagingItems,
            searchQuery = "Search",
            onQueryChange = { },
            onTopicClick = { },
        )
    }
}