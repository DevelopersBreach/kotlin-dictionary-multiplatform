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
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.sections_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.table_of_contents
import org.jetbrains.compose.resources.stringResource

@Composable
fun TableOfContents(topic: KotlinTopicDetails) {
    val items = buildList {
        add(stringResource(Res.string.introduction_bullet))
        if (topic.syntax.signature.isNotBlank()) add(stringResource(Res.string.syntax_bullet))
        if (topic.sections.isNotEmpty()) add(stringResource(Res.string.sections_bullet))
        if (topic.pitfalls.isNotEmpty()) add(stringResource(Res.string.pitfalls_bullet))
        if (topic.relatedTopics.isNotEmpty()) add(stringResource(Res.string.related_topics_bullet))
    }

    Text(text = stringResource(Res.string.table_of_contents), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)
    Spacer(Modifier.height(4.dp))
    items.forEach {
        Text(
            text = it,
            modifier = Modifier
                .clickable { /* scroll-to support later */ }
                .padding(vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
    Spacer(Modifier.height(16.dp))
}