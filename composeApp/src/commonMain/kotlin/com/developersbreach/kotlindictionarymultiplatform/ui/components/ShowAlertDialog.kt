package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ShowAlertDialog(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    androidx.compose.material.AlertDialog(
        onDismissRequest = onButtonClick,
        title = { Text(title) },
        text = { Text(description) },
        confirmButton = {
            androidx.compose.material.TextButton(onClick = onButtonClick) {
                Text("OK")
            }
        },
        modifier = modifier,
    )
}