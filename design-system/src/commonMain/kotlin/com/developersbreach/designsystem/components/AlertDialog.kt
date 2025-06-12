package com.developersbreach.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KdAlertDialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?,
    confirmButton: @Composable () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = title,
        text = text,
        confirmButton = confirmButton,
    )
}

@Composable
fun KdAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    title: String,
    description: String,
    buttonTitle: String,
    onButtonClick: () -> Unit,
) {
    KdAlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            KdText(
                modifier = Modifier,
                text = title,
            )
        },
        text = {
            KdText(
                modifier = Modifier,
                text = description,
            )
        },
        confirmButton = {
            KdTextButton(
                modifier = Modifier,
                onClick = {
                    onDismissRequest()
                    onButtonClick()
                },
                content = {
                    KdText(
                        modifier = Modifier,
                        text = buttonTitle,
                    )
                },
            )
        },
        modifier = modifier,
    )
}