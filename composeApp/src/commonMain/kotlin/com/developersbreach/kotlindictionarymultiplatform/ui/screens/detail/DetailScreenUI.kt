package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.table_of_contents
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailScreenUI(
    detailUiState: DetailUiState,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(
                title = detailUiState.topicName,
                navigateUp = navigateUp,
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        DetailContent(
            detailUiState = detailUiState,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun DetailContent(
    detailUiState: DetailUiState,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        item {
            Text(
                text = stringResource(Res.string.table_of_contents),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(Modifier.height(4.dp))
        }

        items(
            items = detailUiState.tocEntries,
        ) {
            TableOfContents(
                item = stringResource(
                    resource = it.titleRes,
                    formatArgs = it.formatArgs.toTypedArray(),
                ),
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(it.destinationIndex)
                    }
                },
            )
        }

        item {
            Spacer(Modifier.height(16.dp))
        }

        items(
            items = detailUiState.contentItems,
        ) { toc: ItemTableOfContent ->
            when (toc) {
                is ItemTableOfContent.Introduction -> IntroductionSection(toc.intro)
                is ItemTableOfContent.Syntax -> SyntaxSection(toc.syntax)
                is ItemTableOfContent.Section -> SectionBlock(toc.sections)
                is ItemTableOfContent.Pitfall -> PitfallsSection(toc.pitfalls)
                is ItemTableOfContent.RelatedTopic -> RelatedTopicsSection(toc.relatedTopics)
            }
        }
    }
}