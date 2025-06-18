package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic

data class TopicUi(
    val isLoading: Boolean = false,
    val topics: List<Topic> = emptyList(),
    val searchQuery: String = "",
    val filteredTopics: List<ItemTopic> = emptyList(),
)

data class ItemTopic(
    val name: String,
    val initial: String,
    val description: String,
)