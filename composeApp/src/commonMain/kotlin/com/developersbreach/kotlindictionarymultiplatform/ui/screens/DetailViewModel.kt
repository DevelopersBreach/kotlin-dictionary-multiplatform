package com.developersbreach.kotlindictionarymultiplatform.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developersbreach.kotlindictionarymultiplatform.core.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.core.KtorHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.developersbreach.kotlindictionarymultiplatform.core.API_KEY

class DetailViewModel(
    topicId: String

) : ViewModel() {
    private val _state = MutableStateFlow<KotlinTopicDetails?>(null)
    val state: StateFlow<KotlinTopicDetails?> = _state

    init {
        fetchTopic(topicId)
    }
    private fun fetchTopic(topicId: String) {
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
