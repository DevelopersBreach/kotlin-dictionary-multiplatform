package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.developersbreach.kotlindictionarymultiplatform.Log
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppDestinations
import kotlinx.io.IOException

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository,
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<AppDestinations.Detail>().topicId

    private val _state = MutableStateFlow<UiState<KotlinTopicDetails>>(UiState.Loading)
    val state: StateFlow<UiState<KotlinTopicDetails>> = _state

    init {
        viewModelScope.launch {
            try {
                fetchTopic()
            } catch (e: IOException) {
                Log.e("DetailViewModel", "Error fetching topic: ${e.message}", e)
            }
        }
    }

    private suspend fun fetchTopic() {
        val result = repository.fetchTopic(topicId)
        _state.value = result.fold(
            ifLeft = { UiState.Error(it) },
            ifRight = { UiState.Success(it) },
        )
    }
}