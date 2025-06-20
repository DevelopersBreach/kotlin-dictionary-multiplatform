package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler

@Composable
fun TopicScreen(
    viewModel: TopicViewModel,
    onTopicClick: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    UiStateHandler(
        uiState = uiState,
        isLoading = (uiState as? UiState.Success)?.data?.isLoading == true && (uiState as? UiState.Success)?.data?.page == 0
    ) { data ->
        TopicScreenUI(
            topics = data.filteredTopics,
            searchQuery = data.searchQuery,
            onQueryChange = viewModel::updateSearchQuery,
            onTopicClick = onTopicClick,
            onLoadMore = viewModel::loadNextPage,
        )
    }
}

