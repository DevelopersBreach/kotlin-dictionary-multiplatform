package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.TopicUi
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.description_subtitle
import org.jetbrains.compose.resources.stringResource

@Composable
fun TopicList(
    topics: List<TopicUi>,
    bookmarkedStates: List<Boolean>,
    onBookmarkClick: (Int) -> Unit,
    onTopicClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp),
    ) {
        itemsIndexed(topics) { index, topic ->
            val isBookmarked = bookmarkedStates.getOrNull(index) ?: true
            TopicCard(
                topic = topic.name,
                topicUi = topic,
                subtitle = stringResource(Res.string.description_subtitle),
                isBookmarked = isBookmarked,
                onBookmarkClick = { onBookmarkClick(index) },
                onCardClick = { onTopicClick(topic.name) },
            )
        }
    }
}