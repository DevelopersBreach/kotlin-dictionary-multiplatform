package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.developersbreach.designsystem.components.KdAlertDialog
import com.developersbreach.designsystem.components.KdCircularProgressIndicator
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.error_info_unavailable
import kotlindictionarymultiplatform.composeapp.generated.resources.error_occurred
import kotlindictionarymultiplatform.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource

@Composable
fun <T> UiStateHandler(
    uiState: UiState<T>,
    isLoading: Boolean = false,
    content: @Composable (T) -> Unit,
) {
    val shouldDismissErrorDialog = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        shouldDismissErrorDialog.value = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is UiState.Loading -> {
                KdCircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            is UiState.Error -> {
                if (!shouldDismissErrorDialog.value) {
                    val errorDetails = uiState.throwable
                    KdAlertDialog(
                        onDismissRequest = { shouldDismissErrorDialog.value = true },
                        modifier = Modifier,
                        title = stringResource(Res.string.error_occurred),
                        description = errorDetails.message ?: stringResource(Res.string.error_info_unavailable),
                        buttonTitle = stringResource(Res.string.ok),
                        onButtonClick = {
                            shouldDismissErrorDialog.value = true
                        },
                    )
                }
            }

            is UiState.Success -> content(uiState.data)
        }

        if (isLoading) {
            KdCircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}