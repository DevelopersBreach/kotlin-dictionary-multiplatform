package com.developersbreach.designsystem.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun KdCard(
    modifier: Modifier,
    colors: CardColors,
    elevation: CardElevation,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        content = content,
        elevation = elevation,
        colors = colors,
    )
}