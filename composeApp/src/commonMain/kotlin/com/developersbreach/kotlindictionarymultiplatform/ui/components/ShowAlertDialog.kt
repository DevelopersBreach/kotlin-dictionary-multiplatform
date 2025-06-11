package com.developersbreach.kotlindictionarymultiplatform.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.developersbreach.designsystem.components.KdAlertDialog
import com.developersbreach.designsystem.components.KdText
import com.developersbreach.designsystem.components.KdTextButton
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
    KdAlertDialog(
        onDismissRequest = onButtonClick,
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
                onClick = onButtonClick,
                content = {
                    KdText(
                        modifier = Modifier,
                        text = stringResource(Res.string.ok),
                    )
                },
            )
        },
        modifier = modifier,
    )
}