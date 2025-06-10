package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    navigateUp: () -> Unit,
) {
    val topicState by viewModel.state.collectAsState()

    UiStateHandler(uiState = topicState) { detailUiState ->
        DetailScreenUI(
            detailUiState = detailUiState,
            navigateUp = navigateUp,
        )
    }
}