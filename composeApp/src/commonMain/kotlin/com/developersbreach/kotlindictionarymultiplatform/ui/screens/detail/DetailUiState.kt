package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.bullet_item
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics_bullet
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax_bullet
import org.jetbrains.compose.resources.StringResource

data class DetailUiState(
    val tocEntries: List<TocEntry>,
    val contentItems: List<ItemTableOfContent>,
    val topicName: String,
)

sealed class TocItem {
    data object Header : TocItem()

    data class Toc(
        val toc: ItemTableOfContent,
    ) : TocItem()

    data class Content(
        val toc: ItemTableOfContent,
    ) : TocItem()
}

data class TocEntry(
    val titleRes: StringResource,
    val formatArgs: List<Any> = emptyList(),
    val destinationIndex: Int,
)

sealed class ItemTableOfContent {

    abstract val title: StringResource

    data class Introduction(
        val intro: String,
        override val title: StringResource = Res.string.introduction_bullet,
    ) : ItemTableOfContent()

    data class Syntax(
        val syntax: com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Syntax,
        override val title: StringResource = Res.string.syntax_bullet,
    ) : ItemTableOfContent()

    data class Section(
        val index: Int,
        val sections: com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Section,
        override val title: StringResource = Res.string.bullet_item,
    ) : ItemTableOfContent()

    data class Pitfall(
        val pitfalls: List<String>,
        override val title: StringResource = Res.string.pitfalls_bullet,
    ) : ItemTableOfContent()

    data class RelatedTopic(
        val relatedTopics: List<String>,
        override val title: StringResource = Res.string.related_topics_bullet,
    ) : ItemTableOfContent()
}

fun KotlinTopicDetails.toDetailUi(): DetailUiState {
    val contentList = this.toContents()

    val flatList = buildList {
        this.add(TocItem.Header)
        contentList.forEach { this.add(TocItem.Toc(it)) }
        contentList.forEach { this.add(TocItem.Content(it)) }
    }

    val tocEntries = contentList.map { tocItem ->
        TocEntry(
            titleRes = tocItem.title,
            formatArgs = when (tocItem) {
                is ItemTableOfContent.Section -> listOf(tocItem.sections.heading ?: "")
                else -> emptyList()
            },
            destinationIndex = flatList.indexOfFirst {
                it is TocItem.Content && it.toc == tocItem
            },
        )
    }

    return DetailUiState(
        tocEntries = tocEntries,
        contentItems = contentList,
        topicName = topicName,
    )
}

private fun KotlinTopicDetails.toContents(): List<ItemTableOfContent> {
    return buildList {
        add(ItemTableOfContent.Introduction(intro = intro))
        if (syntax.signature.isNotBlank()) add(ItemTableOfContent.Syntax(syntax))
        sections.forEachIndexed { index, section ->
            section.heading?.let {
                add(ItemTableOfContent.Section(index, section))
            }
        }
        if (pitfalls.isNotEmpty()) add(ItemTableOfContent.Pitfall(pitfalls))
        if (relatedTopics.isNotEmpty()) add(ItemTableOfContent.RelatedTopic(relatedTopics))
    }
}