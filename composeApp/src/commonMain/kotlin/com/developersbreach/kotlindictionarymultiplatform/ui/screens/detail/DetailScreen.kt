package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler

@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val uiState by viewModel.state.collectAsState()

    UiStateHandler(uiState = uiState) { topic ->
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item {
                Text(text = topic.topicName, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = topic.intro)
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Signature: ${topic.syntax.signature}",
                    style = MaterialTheme.typography.subtitle1,
                )
                topic.syntax.notes?.let {
                    Text(text = "Notes: $it")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(topic.sections) { section ->
                section.heading?.let { Text(text = it, style = MaterialTheme.typography.h6) }
                section.content?.let { Text(text = it) }

                section.codeExamples.forEach { example ->
                    example.description?.let { Text(text = "Description: $it") }
                    Text(text = example.code, style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (topic.pitfalls.isNotEmpty()) {
                item {
                    Text(text = "Pitfalls", style = MaterialTheme.typography.h6)
                    topic.pitfalls.forEach { Text("- $it") }
                }
            }

            if (topic.relatedTopics.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Related Topics", style = MaterialTheme.typography.h6)
                    topic.relatedTopics.forEach { Text("• $it") }
                }
            }
        }
    }
}