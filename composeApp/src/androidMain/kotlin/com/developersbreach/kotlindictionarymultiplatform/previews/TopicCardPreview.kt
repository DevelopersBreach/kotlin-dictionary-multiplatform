package com.developersbreach.kotlindictionarymultiplatform.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicCard
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme

@Preview(name = "TopicCard - Light", showBackground = true)
@Composable
fun TopicCardLightPreview() {
    KotlinDictionaryTheme(useDarkTheme = false) {
        TopicCard(
            topic = topic(),
            subtitle = subtitle(),
            isBookmarked = false,
            onBookmarkClick = {},
            onCardClick = {},
        )
    }
}

@Preview(name = "TopicCard - Dark", showBackground = true)
@Composable
fun TopicCardDarkPreview() {
    KotlinDictionaryTheme(useDarkTheme = true) {
        TopicCard(
            topic = topic(),
            subtitle = subtitle(),
            isBookmarked = true,
            onBookmarkClick = {},
            onCardClick = {},
        )
    }
}