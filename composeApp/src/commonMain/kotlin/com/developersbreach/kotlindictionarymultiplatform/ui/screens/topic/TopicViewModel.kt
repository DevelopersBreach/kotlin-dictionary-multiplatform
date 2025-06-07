package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {

    private val _topics = MutableStateFlow<UiState<List<Topic>>>(UiState.Loading)
    val topics: StateFlow<UiState<List<Topic>>> = _topics

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _bookmarkedStates = MutableStateFlow<List<Boolean>>(emptyList())
    val bookmarkedStates: StateFlow<List<Boolean>> = _bookmarkedStates

    val filteredTopics: StateFlow<List<Topic>> = combine(_topics, _searchQuery) { uiState, query ->
        val allTopics = (uiState as? UiState.Success)?.data ?: return@combine emptyList()
        if (query.isBlank()) {
            allTopics
        } else {
            allTopics.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            try {
                fetchTopicList()
            } catch (e: IOException) {
                Log.e("TopicViewModel", "Error fetching topics: ${e.message}", e)
            }
        }
    }

    private fun fetchTopicList() {
        val result = repository.getTopics()
        _topics.value = result.fold(
            ifLeft = { UiState.Error(it) },
            ifRight = {
                _bookmarkedStates.value = List(it.size) { true }
                UiState.Success(it)
            },
        )
    }

    fun updateSearchQuery(
        newQuery: String,
    ) {
        _searchQuery.value = newQuery
    }

    fun toggleBookmark(
        index: Int,
    ) {
        _bookmarkedStates.update { current ->
            if (index in current.indices) {
                current.toMutableList().apply { this[index] = !this[index] }
            } else {
                current
            }
        }
    }
}