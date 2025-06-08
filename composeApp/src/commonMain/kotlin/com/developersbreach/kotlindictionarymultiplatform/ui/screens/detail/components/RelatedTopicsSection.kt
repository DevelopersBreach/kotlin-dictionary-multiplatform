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
import kotlindictionarymultiplatform.composeapp.generated.resources.bullet_item
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics
import org.jetbrains.compose.resources.stringResource

@Composable
fun RelatedTopicsSection(
    topic: KotlinTopicDetails,
) {
    if (topic.relatedTopics.isNotEmpty()) {
        Text(
            text = stringResource(resource = Res.string.related_topics),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(Modifier.height(4.dp))
        topic.relatedTopics.forEach {
            Text(
                text = stringResource(
                    Res.string.bullet_item,
                    it,
                ),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}