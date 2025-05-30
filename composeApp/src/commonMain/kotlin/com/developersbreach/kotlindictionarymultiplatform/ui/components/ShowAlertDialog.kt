package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource

@Composable
fun ShowAlertDialog(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    AlertDialog(
        onDismissRequest = onButtonClick,
        title = { Text(title) },
        text = { Text(description) },
        confirmButton = {
            TextButton(onClick = onButtonClick) {
                Text(text = stringResource(Res.string.ok))
            }
        },
        modifier = modifier,
    )
}