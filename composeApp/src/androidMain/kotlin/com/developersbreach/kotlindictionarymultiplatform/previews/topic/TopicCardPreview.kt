package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicUi
import com.developersbreach.kotlindictionarymultiplatform.previews.subtitle
import com.developersbreach.kotlindictionarymultiplatform.previews.topic
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
            topicUi = TopicUi(
                name = "Emerson Vega",
                initial = "senectus",
                isBookmarked = false,
            ),
        )
    }
}