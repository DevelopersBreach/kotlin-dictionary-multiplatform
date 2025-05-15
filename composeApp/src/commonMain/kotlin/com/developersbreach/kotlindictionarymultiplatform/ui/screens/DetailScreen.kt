package com.developersbreach.kotlindictionarymultiplatform.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val topicState by viewModel.state.collectAsState()
    println("check topicState: $topicState")


    topicState?.let { topic ->
        println("Topic data fetched: $topic")
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item {
                Text(text = topic.topicName, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = topic.intro)
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Signature: ${topic.syntax.signature}", style = MaterialTheme.typography.subtitle1)
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
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
