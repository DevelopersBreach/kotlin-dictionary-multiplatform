package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler

@Composable
fun TopicScreen(
    viewModel: TopicViewModel,
    onTopicClick: (String) -> Unit,
) {
    val topicState by viewModel.topics.collectAsState()
    val filteredTopics by viewModel.filteredTopics.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val bookmarkedStates by viewModel.bookmarkedStates.collectAsState()

    UiStateHandler(uiState = topicState) {
        TopicScreenUI(
            topics = filteredTopics,
            bookmarkedStates = bookmarkedStates,
            searchQuery = searchQuery,
            onQueryChange = viewModel::updateSearchQuery,
            onBookmarkClick = viewModel::toggleBookmark,
            onTopicClick = onTopicClick,
        )
    }
}