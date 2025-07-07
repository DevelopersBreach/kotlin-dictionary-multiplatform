package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.ItemTopic
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicCard

@Composable
fun TopicList(
    topics: List<Topic>,
    onTopicClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp),
    ) {
        items(topics) { topic ->
            if (!topic.name.isNullOrEmpty() && !topic.description.isNullOrEmpty()) {
                TopicCard(
                    topic = topic.name,
                    description = topic.description,
                    itemTopic = topic.toItemTopic(),
                    onCardClick = { onTopicClick(topic.name) },
                )
            }
        }
    }
}

fun Topic.toItemTopic(): ItemTopic {
    return ItemTopic(
        name = this.name ?: "",
        initial = this.name?.firstOrNull()?.uppercase() ?: "",
        description = this.description ?: "",
    )
}