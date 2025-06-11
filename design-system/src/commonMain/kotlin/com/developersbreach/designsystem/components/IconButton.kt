package com.developersbreach.designsystem.components

import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun CaIconButton(
    onClick: () -> Unit,
    imageVector: ImageVector,
    contentDescription: String?,
    tint: Color= LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        content = {
            CaIcon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = tint
            )
        }
    )
}
