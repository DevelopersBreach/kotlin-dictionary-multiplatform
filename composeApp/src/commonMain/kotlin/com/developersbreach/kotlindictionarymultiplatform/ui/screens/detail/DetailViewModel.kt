package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.detail.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.core.KtorHttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.developersbreach.kotlindictionarymultiplatform.core.API_KEY
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppDestinations

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<AppDestinations.Detail>().topicId

    private val _state = MutableStateFlow<KotlinTopicDetails?>(null)
    val state: StateFlow<KotlinTopicDetails?> = _state

    init {
        fetchTopic(topicId)
    }

    private fun fetchTopic(topicId: String) {
        viewModelScope.launch {
            try {
                val topic = KtorHttpClient.generateTopicDetails(topicId, API_KEY)
                Log.i("DetailViewModel", "Fetched details: $topic")
                _state.value = topic
                Log.i("DetailViewModel", "State updated with topic: $topic")
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error fetching topic: ${e.message}", e)
            }
        }
    }
}