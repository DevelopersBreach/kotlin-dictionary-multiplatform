package com.developersbreach.kotlindictionarymultiplatform.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdCard
import com.developersbreach.designsystem.components.KdText

@Composable
fun HomeCard(
    label: String,
) {
    KdCard(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(22.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                MaterialTheme.colorScheme.primaryContainer,
                            ),
                        ),
                    ),
                contentAlignment = Alignment.BottomStart,
            ) {
                KdText(
                    text = label,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.BottomStart),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
            }
        },
    )
}