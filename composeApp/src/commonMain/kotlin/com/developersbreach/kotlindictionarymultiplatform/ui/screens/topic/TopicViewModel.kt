package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import kotlinx.coroutines.launch
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState: MutableStateFlow<UiState<TopicUi>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TopicUi>> = _uiState.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagingDataFlow: Flow<PagingData<ItemTopic>> = searchQuery
        .flatMapLatest { query ->
            Pager(
                config = PagingConfig(pageSize = 8),
                pagingSourceFactory = { TopicPagingSource(repository, query) },
            ).flow
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PagingData.empty(),
        )

    init {
        viewModelScope.launch {
            fetchTopicList()
        }
    }

    private suspend fun fetchTopicList() {
        _uiState.value = UiState.Success(TopicUi(isLoading = true))
        repository.getTopics().fold(
            ifLeft = { _uiState.value = UiState.Error(it) },
            ifRight = { list ->
                val sorted = list.sortedBy { it.name?.lowercase() ?: "" }
                applyFilters(sorted, _searchQuery.value)
            },
        )
    }

    fun updateSearchQuery(
        newQuery: String,
    ) {
        _searchQuery.value = newQuery
        // Optionally, you may want to refresh the paging source here if needed
    }

    private fun applyFilters(
        topics: List<Topic>,
        query: String,
    ) {
        val filtered = topics
            .filter { it.name?.contains(query, ignoreCase = true) == true }
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