package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.developersbreach.designsystem.components.KdText
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
        title = { KdText(text = title) },
        text = { KdText(text = description) },
        confirmButton = {
            TextButton(onClick = onButtonClick) {
                KdText(text = stringResource(Res.string.ok))
            }
        },
        modifier = modifier,
    )
}