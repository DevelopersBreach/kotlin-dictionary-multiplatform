package com.developersbreach.kotlindictionarymultiplatform.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopicListScreen(
    onTopicClick: (String) -> Unit
) {

    val topics = listOf(
        "Variables", "Strings", "Functions", "Coroutines",
        "Classes", "Interfaces", "Objects", "Collections",
        "Null Safety", "Lambdas", "Higher-Order Functions",
        "Delegation", "Sealed Classes", "Generics", "Annotations"
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(topics) { topic ->
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onTopicClick(topic)}
            ) {
                Text(
                    text = topic,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
