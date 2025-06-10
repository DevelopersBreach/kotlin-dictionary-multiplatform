package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<TopicUi>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TopicUi>> = _uiState

    private var rawTopics: List<Topic> = emptyList()

    init {
        viewModelScope.launch {
            fetchTopicList()
        }
    }

    private fun fetchTopicList() {
        _uiState.value = UiState.Success(TopicUi(isLoading = true))
        repository.getTopics().fold(
            ifLeft = { UiState.Error(it) },
            ifRight = { list ->
                rawTopics = list.sortedBy { it.name.lowercase() }
                val initialBookmarks = List(rawTopics.size) { true }
                applyFilters(rawTopics, (_uiState.value as UiState.Success).data.searchQuery, initialBookmarks)
            },
        )
    }

    fun updateSearchQuery(
        newQuery: String,
    ) {
        val bookmarks = (_uiState.value as UiState.Success).data.bookmarkedStates
        applyFilters(rawTopics, newQuery, bookmarks)
    }

    fun toggleBookmark(
        index: Int,
    ) {
        val current = (_uiState.value as UiState.Success).data.bookmarkedStates
        if (index !in current.indices) return
        val updated = current.toMutableList().apply { this[index] = !this[index] }
        applyFilters(rawTopics, (_uiState.value as UiState.Success).data.searchQuery, updated)
    }

    private fun applyFilters(
        topics: List<Topic>,
        query: String,
        bookmarks: List<Boolean>,
    ) {
        val filtered = topics
            .withIndex()
            .filter { (_, topic) ->
                topic.name.contains(query, ignoreCase = true)
            }
            .map { (index, topic) ->
                ItemTopic(
                    name = topic.name,
                    initial = topic.name.first().uppercase(),
                    isBookmarked = bookmarks.getOrNull(index) ?: false,
                )
            }

        _uiState.value = (_uiState.value as UiState.Success).copy(
            TopicUi(
                isLoading = false,
                searchQuery = query,
                topics = topics,
                bookmarkedStates = bookmarks,
                filteredTopics = filtered,
            ),
        )
    }
}