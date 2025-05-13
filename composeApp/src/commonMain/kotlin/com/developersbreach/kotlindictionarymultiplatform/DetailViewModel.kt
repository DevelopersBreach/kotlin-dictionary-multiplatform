package com.developersbreach.kotlindictionarymultiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val API_KEY: String) : ViewModel() {
    private val _state = MutableStateFlow<KotlinTopicDetails?>(null)
    val state: StateFlow<KotlinTopicDetails?> = _state

    fun fetchTopic(topicId: String) {
        viewModelScope.launch {
            try {
                val topic = KtorHttpClient.generateTopicDetails(topicId, API_KEY)
                println("Fetched details: $topic")
                _state.value = topic
                println("State updated with topic: $topic")
            } catch (e: Exception) {
                println("Error fetching topic: ${e.message}")
            }
        }
    }
}
