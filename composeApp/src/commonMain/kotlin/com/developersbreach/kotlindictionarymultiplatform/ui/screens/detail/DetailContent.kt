package com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.developersbreach.kotlindictionarymultiplatform.data.detail.model.KotlinTopicDetails
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.IntroductionSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.PitfallsSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.RelatedTopicsSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.SectionsList
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.SyntaxSection
import com.developersbreach.kotlindictionarymultiplatform.ui.screens.detail.components.TableOfContents

@Composable
fun DetailContent(
    topic: KotlinTopicDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        TableOfContents(topic)
        IntroductionSection(topic)
        SyntaxSection(topic)
        SectionsList(topic)
        PitfallsSection(topic)
        RelatedTopicsSection(topic)
    }
}