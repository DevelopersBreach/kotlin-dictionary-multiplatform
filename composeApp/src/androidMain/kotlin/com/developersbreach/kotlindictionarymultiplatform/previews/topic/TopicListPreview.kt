package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.previews.sampleTopicList
import com.developersbreach.kotlindictionarymultiplatform.previews.sampleTopicUiList
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicList
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun TopicListPreview() {
    KotlinDictionaryTheme {
        TopicList(
            topics = sampleTopicUiList(),
            bookmarkedStates = List(sampleTopicList().size) { true },
            onBookmarkClick = {},
            onTopicClick = {},
        )
    }
}