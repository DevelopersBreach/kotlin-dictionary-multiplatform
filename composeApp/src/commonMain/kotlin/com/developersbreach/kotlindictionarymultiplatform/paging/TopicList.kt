package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.ItemTopic
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicCard

@Composable
fun TopicList(
    topics: List<ItemTopic>,
    onTopicClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp),
    ) {
        items(topics) { topic ->
            TopicCard(
                topic = topic.name,
                itemTopic = topic,
                description = topic.description,
                onCardClick = { onTopicClick(topic.name) },
            )
        }
    }
}