package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.font.FontWeight
import com.developersbreach.kotlindictionarymultiplatform.theme.White

@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val topicState by viewModel.state.collectAsState()

    topicState?.let { topic ->
        val scrollState = rememberScrollState()
        val clipboardManager = LocalClipboardManager.current

        Scaffold(
            backgroundColor = White,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = topic.topicName,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFF0F2851),
                        )
                    },
                    backgroundColor = White,
                    navigationIcon = {
                        IconButton(onClick = {
                            // Handle back navigation
                        }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    },
                    elevation = 0.dp,
                    modifier = Modifier.statusBarsPadding(),
                )
            },
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                        .padding(innerPadding),
                ) {
                    Text(
                        text = "Table of Contents",
                        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
                        color = Color(0xFF0F2851),
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    val tableOfContents = buildList {
                        add("• Introduction")
                        if (topic.syntax.signature.isNotBlank()) add("• Syntax")
                        if (topic.sections.isNotEmpty()) add("• Sections")
                        if (topic.pitfalls.isNotEmpty()) add("• Pitfalls")
                        if (topic.relatedTopics.isNotEmpty()) add("• Related Topics")
                    }

                    tableOfContents.forEach {
                        Text(
                            text = it,
                            modifier = Modifier
                                .clickable { /* Scroll to section logic */ }
                                .padding(vertical = 4.dp),
                            color = Color.Black,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Content Sections
                    Text(text = "Introduction", style = MaterialTheme.typography.h6, color = Color(0xFF0F2851))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = topic.intro)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Syntax", style = MaterialTheme.typography.h6, color = Color(0xFF0F2851))
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = topic.syntax.signature)
                    topic.syntax.notes?.let {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Notes: $it")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    topic.sections.forEach { section ->
                        section.heading?.let {
                            Text(text = it, style = MaterialTheme.typography.h6, color = Color(0xFF0F2851))
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                        section.content?.let {
                            Text(text = it)
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        section.codeExamples.forEach { example ->
                            example.description?.let {
                                Text(text = "Description: $it", fontWeight = FontWeight.Bold)
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFFFE0B2), RoundedCornerShape(8.dp))
                                    .padding(12.dp),
                            ) {
                                Text(
                                    text = example.code,
                                    style = MaterialTheme.typography.body2,
                                    color = Color(0xFF0F2851),
                                )

                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Copy Code",
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .clickable {
                                            clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(example.code))
                                        },
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }

                    if (topic.pitfalls.isNotEmpty()) {
                        Text(text = "Pitfalls", style = MaterialTheme.typography.h6, color = Color(0xFF0F2851))
                        Spacer(modifier = Modifier.height(4.dp))
                        topic.pitfalls.forEach {
                            Text("- $it")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    if (topic.relatedTopics.isNotEmpty()) {
                        Text(text = "Related Topics", style = MaterialTheme.typography.h6, color = Color(0xFF0F2851))
                        Spacer(modifier = Modifier.height(4.dp))
                        topic.relatedTopics.forEach {
                            Text("• $it")
                        }
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                }
            },
        )
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}