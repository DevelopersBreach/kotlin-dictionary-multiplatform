package com.developersbreach.kotlindictionarymultiplatform.paging

import androidx.compose.runtime.*
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreenUI
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TopicScreen(
    onTopicClick: (String) -> Unit,
) {
    val pager = remember { createTopicPager() }
    val topicList = remember { mutableStateListOf<Topic>() }

    LaunchedEffect(Unit) {
        pager.pages().collectLatest { page ->
            topicList.clear()
            topicList.addAll(page.items)
        }
    }

    TopicScreenUI(
        topics = topicList,
        searchQuery = "",
        onQueryChange = {},
        onTopicClick = onTopicClick,
    )
}