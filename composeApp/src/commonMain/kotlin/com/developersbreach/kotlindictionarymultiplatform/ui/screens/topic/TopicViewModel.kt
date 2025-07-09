package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.paging.TopicPager
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopicViewModel(
    private val pager: TopicPager,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<TopicUi>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TopicUi>> = _uiState

    private var rawTopics: MutableList<Topic> = mutableListOf()

    init {
        viewModelScope.launch {
            _uiState.value = UiState.Success(TopicUi(isLoading = true))
            pager.pages().collect { page ->
                rawTopics += page.items
                applyFilters(rawTopics, (_uiState.value as UiState.Success).data.searchQuery)
            }
        }
    }

    fun updateSearchQuery(
        newQuery: String,
    ) {
        applyFilters(rawTopics, newQuery)
    }

    private fun applyFilters(
        topics: List<Topic>,
        query: String,
    ) {
        val filtered = topics
            .filter { topic ->
                topic.name?.contains(query, ignoreCase = true) == true
            }
            .map { topic ->
                ItemTopic(
                    name = topic.name ?: "",
                    initial = topic.name?.firstOrNull()?.uppercase() ?: "",
                    description = topic.description ?: "",
                )
            }

        _uiState.value = UiState.Success(
            TopicUi(
                isLoading = false,
                searchQuery = query,
                topics = topics,
                filteredTopics = filtered,
            ),
        )
    }
}