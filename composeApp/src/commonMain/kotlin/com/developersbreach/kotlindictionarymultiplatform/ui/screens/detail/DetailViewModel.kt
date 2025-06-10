package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.developersbreach.kotlindictionarymultiplatform.data.detail.repository.DetailRepository
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiState
import com.developersbreach.kotlindictionarymultiplatform.ui.navigation.AppDestinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: DetailRepository,
) : ViewModel() {

    private val topicId = savedStateHandle.toRoute<AppDestinations.Detail>().topicId

    private val _state = MutableStateFlow<UiState<DetailUiState>>(UiState.Loading)
    val state: StateFlow<UiState<DetailUiState>> = _state

    init {
        viewModelScope.launch {
            fetchTopic()
        }
    }

    private suspend fun fetchTopic() {
        _state.value = repository.fetchTopic(topicId).fold(
            ifLeft = { UiState.Error(it) },
            ifRight = { UiState.Success(it.toDetailUi()) },
        )
    }
}