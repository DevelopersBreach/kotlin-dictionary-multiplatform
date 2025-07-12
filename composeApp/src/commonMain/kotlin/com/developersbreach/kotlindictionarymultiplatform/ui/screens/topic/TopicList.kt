package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems

@Composable
fun TopicList(
    topics: LazyPagingItems<ItemTopic>,
    onTopicClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 40.dp),
    ) {
        items(topics.itemCount) { index ->
            val topic = topics[index]
            topic?.let {
                TopicCard(
                    topic = it.name,
                    itemTopic = it,
                    description = it.description,
                    onCardClick = { onTopicClick(it.name) },
                )
            }
        }
    }
}