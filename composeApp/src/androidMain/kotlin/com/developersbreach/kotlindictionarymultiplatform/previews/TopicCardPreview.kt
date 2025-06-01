package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicCard
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@PreviewLightDark
@Composable
fun TopicCardPreview() {
    KotlinDictionaryTheme {
        TopicCard(
            topic = topic(),
            subtitle = subtitle(),
            isBookmarked = false,
            onBookmarkClick = {},
            onCardClick = {},
        )
    }
}