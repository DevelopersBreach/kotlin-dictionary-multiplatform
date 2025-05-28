package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppDestinations

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository,
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<AppDestinations.Detail>().topicId

    private val _state = MutableStateFlow<UiState<KotlinTopicDetails>>(UiState.Loading)
    val state: StateFlow<UiState<KotlinTopicDetails>> = _state

    init {
        fetchTopic(topicId)
    }

    private fun fetchTopic(topicId: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading

            val result = repository.fetchTopicSafe(topicId)

            _state.value = result.fold(
                ifLeft = { throwable ->
                    val message = throwable.message?.takeIf { it.isNotBlank() } ?: "Something went wrong"
                    UiState.Error(message)
                },
                ifRight = { topic -> UiState.Success(topic) },
            )
        }
    }
}