package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler

@Composable
fun TopicScreen(
    onTopicClick: (String) -> Unit,
    viewModel: TopicViewModel,
) {
    val state by viewModel.topics.collectAsState()

    UiStateHandler(uiState = state) { topics ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            items(topics) { topic ->
                TopicItem(topic = topic, onClick = {
                    Log.i("TopicScreen", "Topic clicked: ${topic.name}")
                    onTopicClick(topic.name)
                })
            }
        }
    }
}

@Composable
fun TopicItem(
    topic: Topic,
    onClick: () -> Unit,
) {
    Log.i("TopicItem", "Rendering topic item: ${topic.name}")

    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
    ) {
        Text(
            text = topic.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp),
        )
    }
}