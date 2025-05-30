package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.io.IOException

class TopicViewModel(
    private val repository: TopicRepository,
) : ViewModel() {

    private val _topics = MutableStateFlow<UiState<List<Topic>>>(UiState.Loading)
    val topics: StateFlow<UiState<List<Topic>>> = _topics

    init {
        viewModelScope.launch {
            try {
                fetchTopicList()
            } catch (e: IOException) {
                Log.e("TopicViewModel", "Error fetching topics: ${e.message}", e)
            }
        }
    }

    private suspend fun fetchTopicList() {
        val result = repository.getTopics()
        _topics.value = result.fold(
            ifLeft = { UiState.Error(it) },
            ifRight = { UiState.Success(it) },
        )
    }
}