package com.developersbreach.kotlindictionarymultiplatform.previews.topic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.previews.sampleTopicList
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.TopicScreenUI
import com.developersbreach.kotlindictionarymultiplatform.ui.theme.KotlinDictionaryTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// Fake ViewModel for previews
class FakeTopicViewModel : TopicViewModelFakeBase() {
    override val topics = MutableStateFlow<UiState<List<Topic>>>(UiState.Success(sampleTopicList()))
    override val searchQuery = MutableStateFlow("")
    override val filteredTopics = MutableStateFlow(sampleTopicList())
    override val bookmarkedStates = MutableStateFlow(List(sampleTopicList().size) { true })

    override fun updateSearchQuery(newQuery: String) {}

    override fun toggleBookmark(index: Int) {}
}

// Base class to avoid needing Android ViewModel
abstract class TopicViewModelFakeBase {
    abstract val topics: StateFlow<UiState<List<Topic>>>
    abstract val searchQuery: StateFlow<String>
    abstract val filteredTopics: StateFlow<List<Topic>>
    abstract val bookmarkedStates: StateFlow<List<Boolean>>

    abstract fun updateSearchQuery(newQuery: String)

    abstract fun toggleBookmark(index: Int)
}

@Composable
fun TopicScreenContent(
    viewModel: TopicViewModelFakeBase,
    onTopicClick: (String) -> Unit = {},
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

@PreviewLightDark
@Composable
fun TopicScreenPreview() {
    KotlinDictionaryTheme {
        TopicScreenContent(viewModel = FakeTopicViewModel())
    }
}