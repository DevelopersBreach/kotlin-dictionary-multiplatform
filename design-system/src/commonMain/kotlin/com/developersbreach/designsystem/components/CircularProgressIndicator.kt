package com.developersbreach.designsystem.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun KdCircularProgressIndicator(
    modifier: Modifier,
    color: Color,
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
    )
}