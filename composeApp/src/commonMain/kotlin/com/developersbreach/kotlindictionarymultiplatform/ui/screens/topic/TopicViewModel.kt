package com.developersbreach.kotlindictionarymultiplatform.ui.screens.topic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.developersbreach.kotlindictionarymultiplatform.data.topic.model.Topic
import com.developersbreach.kotlindictionarymultiplatform.data.topic.repository.TopicRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TopicViewModel : ViewModel() {

    private val _topics = MutableStateFlow<List<Topic>>(emptyList())
    val topics: StateFlow<List<Topic>> = _topics

    init {
        fetchTopicList()
    }

    private fun fetchTopicList() {
        viewModelScope.launch {
            try {
                val topics = TopicRepository.getTopics()
                Logger.i("TopicFetch") { "Successfully fetched topics: $topics" }
                _topics.value = topics
            } catch (e: Exception) {
                Logger.e("TopicFetch", e) { "Error fetching topics: ${e.message}" }
            }
        }
    }
}