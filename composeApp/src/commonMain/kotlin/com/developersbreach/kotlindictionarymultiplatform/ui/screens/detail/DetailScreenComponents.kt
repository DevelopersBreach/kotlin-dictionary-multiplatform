package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developersbreach.designsystem.components.KdText
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Section
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.Syntax
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.bullet_item
import kotlindictionarymultiplatform.composeapp.generated.resources.introduction
import kotlindictionarymultiplatform.composeapp.generated.resources.notes_with_value
import kotlindictionarymultiplatform.composeapp.generated.resources.pitfalls
import kotlindictionarymultiplatform.composeapp.generated.resources.related_topics
import kotlindictionarymultiplatform.composeapp.generated.resources.syntax
import org.jetbrains.compose.resources.stringResource

@Composable
fun TableOfContents(
    item: String,
    onClick: () -> Unit,
) {
    KdText(
        text = item,
        modifier = Modifier.clickable { onClick() },
        color = MaterialTheme.colorScheme.onSurface,
    )
    Spacer(Modifier.height(4.dp))
}

@Composable
fun IntroductionSection(
    intro: String,
) {
    KdText(
        modifier = Modifier,
        text = stringResource(resource = Res.string.introduction),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(Modifier.height(4.dp))
    KdText(
        modifier = Modifier,
        text = intro,
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(Modifier.height(16.dp))
}

@Composable
fun SyntaxSection(
    syntax: Syntax,
) {
    KdText(
        modifier = Modifier,
        text = stringResource(resource = Res.string.syntax),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(Modifier.height(4.dp))
    KdText(
        modifier = Modifier,
        text = syntax.signature,
        style = MaterialTheme.typography.bodyMedium,
    )
    syntax.notes?.let {
        Spacer(Modifier.height(4.dp))
        KdText(
            modifier = Modifier,
            text = stringResource(
                Res.string.notes_with_value,
                it,
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
fun SectionBlock(
    section: Section,
) {
    section.heading?.let {
        KdText(
            modifier = Modifier,
            text = it,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(Modifier.height(4.dp))
    }

    section.content?.let {
        KdText(
            modifier = Modifier,
            text = it,
            style = MaterialTheme.typography.bodyMedium,
        )
        Spacer(Modifier.height(8.dp))
    }

    section.codeExamples.forEach { example ->
        example.description?.let {
            KdText(
                modifier = Modifier,
                text = it,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Spacer(Modifier.height(16.dp))
        CodeExampleBox(code = example.code)
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun PitfallsSection(
    pitfalls: List<String>,
) {
    KdText(
        modifier = Modifier,
        text = stringResource(resource = Res.string.pitfalls),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(Modifier.height(4.dp))
    pitfalls.forEach {
        KdText(
            modifier = Modifier,
            text = stringResource(
                Res.string.bullet_item,
                it,
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
fun RelatedTopicsSection(
    relatedTopics: List<String>,
) {
    KdText(
        modifier = Modifier,
        text = stringResource(resource = Res.string.related_topics),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(Modifier.height(4.dp))
    relatedTopics.forEach {
        KdText(
            modifier = Modifier,
            text = stringResource(
                Res.string.bullet_item,
                it,
            ),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}