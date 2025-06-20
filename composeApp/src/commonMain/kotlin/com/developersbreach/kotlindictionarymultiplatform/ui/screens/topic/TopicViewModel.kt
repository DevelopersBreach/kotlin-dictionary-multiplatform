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

    private var allTopics: List<Topic> = emptyList()

    companion object {
        private const val PAGE_SIZE = 8
    }

    init {
        viewModelScope.launch {
            fetchTopics()
        }
    }

    private suspend fun fetchTopics() {
        _uiState.value = UiState.Loading

        repository.getTopics().fold(
            ifLeft = {
                _uiState.value = UiState.Error(it)
            },
            ifRight = { topics ->
                allTopics = topics.sortedBy { it.name?.lowercase() ?: "" }

                _uiState.value = UiState.Success(
                    TopicUi(isLoading = false),
                )
                loadNextPage()
            },
        )
    }

    private fun getPagedTopics(
        page: Int,
    ): List<Topic> {
        val start = page * PAGE_SIZE
        val end = minOf(start + PAGE_SIZE, allTopics.size)
        return if (start >= allTopics.size) emptyList() else allTopics.subList(start, end)
    }

    fun loadNextPage() {
        val state = (_uiState.value as? UiState.Success)?.data ?: return
        if (state.isLoading || !state.hasMore) {
            return
        }

        _uiState.value = UiState.Success(state.copy(isLoading = true))

        val newTopics = getPagedTopics(state.page)

        val combined = state.topics + newTopics
        val nextPage = state.page + 1
        val hasMore = newTopics.size == PAGE_SIZE

        applyFilters(
            topics = combined,
            query = state.searchQuery,
            currentPage = nextPage,
            hasMore = hasMore,
        )
    }

    fun updateSearchQuery(
        newQuery: String,
    ) {
        val state = (_uiState.value as? UiState.Success)?.data ?: return
        applyFilters(state.topics, newQuery, state.page, state.hasMore)
    }

    private fun Topic.toItemTopic(): ItemTopic {
        return ItemTopic(
            name = this.name ?: "",
            initial = this.name?.firstOrNull()?.uppercase() ?: "",
            description = this.description ?: "",
        )
    }

    private fun applyFilters(
        topics: List<Topic>,
        query: String,
        currentPage: Int,
        hasMore: Boolean,
    ) {
        val filtered = topics.filter { it.name?.contains(query, ignoreCase = true) == true }.map { it.toItemTopic() }

        _uiState.value = UiState.Success(
            TopicUi(
                isLoading = false,
                searchQuery = query,
                topics = topics,
                filteredTopics = filtered,
                page = currentPage,
                hasMore = hasMore,
            ),
        )
    }
}