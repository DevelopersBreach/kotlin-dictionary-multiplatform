package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.notes_with_value
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax
import org.jetbrains.compose.resources.stringResource

@Composable
fun SyntaxSection(topic: KotlinTopicDetails) {
    Text(stringResource(Res.string.syntax), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
    Spacer(Modifier.height(4.dp))
    Text(text = topic.syntax.signature, style = MaterialTheme.typography.bodyMedium)

    topic.syntax.notes?.let {
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(Res.string.notes_with_value, it),
            style = MaterialTheme.typography.bodyMedium,
        )
    }

    Spacer(Modifier.height(16.dp))
}