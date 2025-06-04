package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.CodeExampleBox

@Composable
fun SectionsList(topic: KotlinTopicDetails) {
    topic.sections.forEach { section ->
        section.heading?.let {
            Text(it, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
            Spacer(Modifier.height(4.dp))
        }
        section.content?.let {
            Text(it, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
        }
        section.codeExamples.forEach { example ->
            example.description?.let {
                Text(it, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(Modifier.height(16.dp))
            CodeExampleBox(code = example.code)
            Spacer(Modifier.height(16.dp))
        }
    }
}