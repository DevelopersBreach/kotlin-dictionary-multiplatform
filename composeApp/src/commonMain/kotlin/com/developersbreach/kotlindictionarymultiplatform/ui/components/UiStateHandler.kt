package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun <T> UiStateHandler(
    uiState: UiState<T>,
    isLoading: Boolean = false,
    content: @Composable (T) -> Unit,
) {
    val shouldDismissErrorDialog = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Error) {
            shouldDismissErrorDialog.value = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        when (uiState) {
            is UiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colors.onBackground,
                )
            }

            is UiState.Error -> {
                if (!shouldDismissErrorDialog.value) {
                    val errorDetails = uiState.message
                    ShowAlertDialog(
                        onButtonClick = { shouldDismissErrorDialog.value = true },
                        modifier = Modifier,
                        title = "Error occurred",
                        description = errorDetails,
                    )
                }
            }

            is UiState.Success -> content(uiState.data)
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.onBackground,
            )
        }
    }
}