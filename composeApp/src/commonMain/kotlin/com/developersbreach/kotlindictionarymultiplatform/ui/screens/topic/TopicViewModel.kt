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

    private val _topics = MutableStateFlow<UiState<List<Topic>>>(UiState.Loading)
    val topics: StateFlow<UiState<List<Topic>>> = _topics

    init {
        fetchTopicList()
    }

    private fun fetchTopicList() {
        viewModelScope.launch {
            _topics.value = UiState.Loading

            val result = repository.getTopicsSafe()

            _topics.value = result.fold(
                ifLeft = { throwable ->
                    val message = throwable.message?.takeIf { it.isNotBlank() } ?: "Something went wrong"
                    UiState.Error(message)
                },
                ifRight = { list -> UiState.Success(list) },
            )
        }
    }
}