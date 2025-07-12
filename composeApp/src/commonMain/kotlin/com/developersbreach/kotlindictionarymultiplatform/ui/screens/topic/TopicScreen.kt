package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.cash.paging.compose.collectAsLazyPagingItems

@Composable
fun TopicScreen(
    viewModel: TopicViewModel,
    onTopicClick: (String) -> Unit,
) {
    val pagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()
    val searchQuery = viewModel.searchQuery.collectAsState().value

    TopicScreenUI(
        topics = pagingItems,
        searchQuery = searchQuery,
        onQueryChange = viewModel::updateSearchQuery,
        onTopicClick = onTopicClick,
    )
}