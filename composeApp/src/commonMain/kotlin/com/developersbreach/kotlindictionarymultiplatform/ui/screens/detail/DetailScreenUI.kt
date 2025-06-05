package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails

@Composable
fun DetailScreenUI(topic: KotlinTopicDetails) {
    Scaffold(
        topBar = { DetailTopBar(title = topic.topicName) },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        DetailContent(
            topic = topic,
            modifier = Modifier.padding(innerPadding),
        )
    }
}