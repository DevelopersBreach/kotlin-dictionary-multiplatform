package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.IntroductionSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.PitfallsSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.RelatedTopicsSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.SectionBlock
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.SyntaxSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.TableOfContents
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.bullet_item
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax_bullet
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun DetailContent(
    topic: KotlinTopicDetails,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val tocItems = list(topic)
    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        items(
            tocItems
        ){

            when(it){
                DetailViewModel.ItemTableOfContent.Introduction(intro = topic.intro) -> IntroductionSection(it.)
                DetailViewModel.ItemTableOfContent.Pitfall -> SyntaxSection(it.)
                DetailViewModel.ItemTableOfContent.RelatedTopic -> TODO()
                is DetailViewModel.ItemTableOfContent.Section -> TODO()
                DetailViewModel.ItemTableOfContent.Syntax -> TODO()
            }
        }

        // Table of Contents
        item {
            TableOfContents(
                items = tocItems,
                onClick = { index ->
                    coroutineScope.launch {
                        listState.animateScrollToItem(index)
                    }
                },
            )
        }

        // Sections
        item { IntroductionSection(topic) }

        if (topic.syntax.signature.isNotBlank()) {
            item { SyntaxSection(topic) }
        }

        topic.sections.forEach { section ->
            item { SectionBlock(section) }
        }

        if (topic.pitfalls.isNotEmpty()) {
            item { PitfallsSection(topic) }
        }

        if (topic.relatedTopics.isNotEmpty()) {
            item { RelatedTopicsSection(topic) }
        }
    }
}