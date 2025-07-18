package com.developersbreach.kotlindictionarymultiplatform.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic.Topic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val topicRepository: TopicRepository,
) : ViewModel() {

    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics.asStateFlow()

    init {
        fetchTopics()
    }

    private fun fetchTopics() {
        viewModelScope.launch {
            val result = topicRepository.getTopicsPage(page = 1, pageSize = 5, query = "")
            _topics.value = result
        }
    }
}