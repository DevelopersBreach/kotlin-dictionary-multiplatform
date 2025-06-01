package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developersbreach.kotlindictionarymultiplatform.ui.components.UiStateHandler
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.back
import kotlindictionarymultiplatform.composeapp.generated.resources.bullet_item
import kotlindictionarymultiplatform.composeapp.generated.resources.copied
import kotlindictionarymultiplatform.composeapp.generated.resources.copy
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.kotlin
import kotlindictionarymultiplatform.composeapp.generated.resources.notes_with_value
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.sections_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.table_of_contents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val topicState by viewModel.state.collectAsState()

    UiStateHandler(uiState = topicState) { topic ->
        val scrollState = rememberScrollState()

        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = topic.topicName,
                            style = MaterialTheme.typography.displayMedium,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Back action */ }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(Res.string.back))
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp)
                    .padding(innerPadding),
            ) {
                Text(
                    text = stringResource(Res.string.table_of_contents),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Spacer(modifier = Modifier.height(4.dp))

                val tableOfContents = buildList {
                    add(stringResource(Res.string.introduction_bullet))
                    if (topic.syntax.signature.isNotBlank()) add(stringResource(Res.string.syntax_bullet))
                    if (topic.sections.isNotEmpty()) add(stringResource(Res.string.sections_bullet))
                    if (topic.pitfalls.isNotEmpty()) add(stringResource(Res.string.pitfalls_bullet))
                    if (topic.relatedTopics.isNotEmpty()) add(stringResource(Res.string.related_topics_bullet))
                }

                tableOfContents.forEach {
                    Text(
                        text = it,
                        modifier = Modifier
                            .clickable { }
                            .padding(vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(stringResource(Res.string.introduction), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = topic.intro,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(stringResource(Res.string.syntax), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = topic.syntax.signature,
                    style = MaterialTheme.typography.bodyMedium,
                )
                topic.syntax.notes?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(Res.string.notes_with_value, it),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                topic.sections.forEach { section ->
                    section.heading?.let {
                        Text(it, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    section.content?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    section.codeExamples.forEach { example ->
                        example.description?.let {
                            Text(
                                it,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        CodeExampleBox(code = example.code)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                if (topic.pitfalls.isNotEmpty()) {
                    Text(stringResource(Res.string.pitfalls), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.height(4.dp))
                    topic.pitfalls.forEach {
                        Text(
                            stringResource(Res.string.bullet_item, it),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                if (topic.relatedTopics.isNotEmpty()) {
                    Text(stringResource(Res.string.related_topics), style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.height(4.dp))
                    topic.relatedTopics.forEach {
                        Text(
                            stringResource(Res.string.bullet_item, it),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CodeExampleBox(
    code: String,
    modifier: Modifier = Modifier,
) {
    val clipboardManager = LocalClipboardManager.current
    var copied by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.onPrimaryContainer, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp)),
    ) {
        Column(modifier = Modifier.padding(0.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimaryContainer, RoundedCornerShape(8.dp))
                    .padding(6.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = stringResource(Res.string.kotlin),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 4.dp),
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable {
                                clipboardManager.setText(AnnotatedString(code))
                                copied = true
                                CoroutineScope(Dispatchers.Main).launch {
                                    delay(2500)
                                    copied = false
                                }
                            }
                            .padding(4.dp),
                    ) {
                        Icon(
                            imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                            contentDescription = stringResource(Res.string.copy),
                            tint = MaterialTheme.colorScheme.onPrimary,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (copied) stringResource(Res.string.copied) else stringResource(Res.string.copy),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = code,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}