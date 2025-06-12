package com.developersbreach.designsystem.components

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

@Composable
fun KdSurface(
    modifier: Modifier,
    color: Color,
    shape: Shape,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        content = content,
        shape = shape,
        color = color,
    )
}