package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.table_of_contents
import org.jetbrains.compose.resources.stringResource

@Composable
fun TableOfContents(
    items: List<Pair<String, Int>>,
    onClick: (Int) -> Unit,
) {
    Text(
        text = stringResource(Res.string.table_of_contents),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(Modifier.height(4.dp))
    items.forEach { (label, index) ->
        Text(
            text = label,
            modifier = Modifier
                .clickable { onClick(index) }
                .padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
    Spacer(Modifier.height(16.dp))
}