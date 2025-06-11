package com.developersbreach.designsystem.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun CaSurface(
    modifier: Modifier,
    content: @Composable () -> Unit,
    color: Color,
    shape: Shape,
) {
    Surface(
        modifier = modifier,
        content = content,
        shape = shape,
        color = color,
    )
}